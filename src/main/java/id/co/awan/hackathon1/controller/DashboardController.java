package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard/{address}")
@RequiredArgsConstructor
public class DashboardController {

    private final AttendRepository attendRepository;
    private final EnrollRepository enrollRepository;
    private final EventRepository eventRepository;
    private final OrganizerClaimHistoryRepository organizerClaimHistoryRepository;

    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    // DONE
    @Operation(
            summary = "Mendapatkan data dashboard sebagai participant"
    )
    @GetMapping(path = "/participant")
    public ResponseEntity<GetDashboardP> getDashboardViewParticipant(
            @PathVariable(name = "address")
            String participantAddress
    ) {


        /*
         * ======================================================================================
         *                    Participant Validation
         * ======================================================================================
         * */

        // Akan Zero jika participant tidak pernah enroll event
        // Jadi di error kan saja
        BigInteger totalCommitmentFee = enrollRepository.totalCommitmentFee(participantAddress)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "participant don't have enrolled event"
                ));

        // Akan Zero jika participant tidak pernah attend event
        BigInteger totalClaimedCommitmentFee = attendRepository.totalClaimedCommitmentFee(participantAddress)
                .orElse(BigInteger.ZERO);

        /*
         * ======================================================================================
         *                     Participant Statistic
         * ======================================================================================
         * */

        // Commitment yang belum diklaim = tot commitment fee - claimed commitment fee
        BigInteger unclaimedCommitmentFee = totalCommitmentFee.subtract(totalClaimedCommitmentFee);
        GetDashboardPStatistic statistic = new GetDashboardPStatistic(
                null, //Gak relevan,
                unclaimedCommitmentFee,
                totalClaimedCommitmentFee
        );

        /*
         * ======================================================================================
         *                     Participant Upcoming Session
         * ======================================================================================
         * */
        List<GetDashboardPUpcomingSession> upcomingSessions = enrollRepository.upcomingSessionByEnrolled(participantAddress)
                .stream().map(session -> {

                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);

                    if (event == null) {
                        return null;
                    }

                    long startSessionTime = session.getStartSessionTime().longValue();
                    long endSessionTime = session.getEndSessionTime().longValue();
                    long durationInSeconds = endSessionTime - startSessionTime;

                    return new GetDashboardPUpcomingSession(
                            event.getId(),
                            event.getTitle(),
                            session.getSession(),
                            session.getTitle(),
                            session.getStartSessionTime(),
                            session.getEndSessionTime(),
                            humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                            humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                            Math.floorDiv((int) durationInSeconds, 3600),
                            Math.floorDiv((int) (durationInSeconds % 3600), 60)
                    );
                })
                .toList();

        /*
         * ======================================================================================
         *                     Participant Completed Session
         * ======================================================================================
         * */
        List<GetDashboardPCompletedSession> completedSession = enrollRepository.completedSessionByEnrolled(participantAddress)
                .stream().map(session -> {

                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);

                    if (event == null) {
                        return null;
                    }

                    return new GetDashboardPCompletedSession(
                            event.getId(),
                            event.getTitle(),
                            session.getSession(),
                            session.getTitle(),
                            session.getStartSessionTime(),
                            session.getEndSessionTime(),
                            humanReadableFormatter.format(Instant.ofEpochSecond(session.getStartSessionTime().longValue())),
                            humanReadableFormatter.format(Instant.ofEpochSecond(session.getEndSessionTime().longValue())),
                            null
                    );
                })
                .toList();

        /*
         * ======================================================================================
         *                      Construct Response GetDashboardP
         * ======================================================================================
         * */
        GetDashboardP getDashboardP = new GetDashboardP(statistic, upcomingSessions, completedSession);
        return ResponseEntity.ok(getDashboardP);

    }

    // DONE
    @Operation(
            summary = "Mendapatkan data dashboard sebagai event organizer"
    )
    @GetMapping(path = "/organizer")
    public ResponseEntity<GetDashboardEO> getDashboardViewEventOrganizer(
            @PathVariable(name = "address")
            String organizerAddress
    ) {


        /*
         * ======================================================================================
         *                     Organizer Statistic
         * ======================================================================================
         * */

        BigInteger totalRevenue = eventRepository.totalRevenueOfOrganizer(organizerAddress);
        BigInteger totalClaimedRevenue = organizerClaimHistoryRepository.totalClaimedRevenue(organizerAddress);

        // Commitment yang belum diklaim = tot commitment fee - claimed commitment fee
        GetDashboardEOStatistic statistic = new GetDashboardEOStatistic(
                totalRevenue,
                totalRevenue.subtract(totalClaimedRevenue),
                totalClaimedRevenue
        );

        /*
         * ======================================================================================
         *                     Organizer Upcoming Session
         * ======================================================================================
         * */
        List<GetDashboardEOUpcomingSession> upcomingSession = eventRepository.upcomingSessionByOrganizer(organizerAddress)
                .stream().map(session -> {

                            Event event = eventRepository.findById(session.getId())
                                    .orElse(null);

                            if (event == null) {
                                return null;
                            }

                            long startSessionTime = session.getStartSessionTime().longValue();
                            long endSessionTime = session.getEndSessionTime().longValue();
                            long durationInSeconds = endSessionTime - startSessionTime;

                            return new GetDashboardEOUpcomingSession(
                                    event.getId(),
                                    event.getTitle(),
                                    session.getSession(),
                                    session.getTitle(),
                                    session.getStartSessionTime(),
                                    session.getStartSessionTime(),
                                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                                    Math.floorDiv((int) durationInSeconds, 3600),
                                    Math.floorDiv((int) (durationInSeconds % 3600), 60)
                            );
                        }
                )
                .toList();

        /*
         * ======================================================================================
         *                     Organizer Completed Session
         * ======================================================================================
         * */
        List<GetDashboardEOCompletedSession> completededSession = eventRepository.completedSessionByOrganizer(organizerAddress)
                .stream().map(session -> {

                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);

                    if (event == null) {
                        return null;
                    }

                    return new GetDashboardEOCompletedSession(
                            event.getId(),
                            event.getTitle(),
                            session.getSession(),
                            session.getTitle(),
                            session.getStartSessionTime(),
                            session.getEndSessionTime(),
                            humanReadableFormatter.format(Instant.ofEpochSecond(session.getStartSessionTime().longValue())),
                            humanReadableFormatter.format(Instant.ofEpochSecond(session.getEndSessionTime().longValue())),
                            // TODO: Complete this logic
                            null
                    );


                })
                .toList();

        /*
         * ======================================================================================
         *                      Construct Response GetDashboardEO
         * ======================================================================================
         * */
        GetDashboardEO getDashboardEO = new GetDashboardEO(statistic, upcomingSession, completededSession);
        return ResponseEntity.ok(getDashboardEO);
    }

}
