package id.co.awan.hackathon1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard/{address}")
@RequiredArgsConstructor
public class DashboardController {

    //    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;
    private final AttendRepository attendRepository;
    private final EnrollRepository enrollRepository;
    private final SessionRepository sessionRepository;
    private final EventRepository eventRepository;

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


        BigInteger totalCommitmentFee = enrollRepository.totalCommitmentFee(participantAddress)
                .orElse(BigInteger.ZERO);
        BigInteger totalClaimedCommitmentFee = attendRepository.totalClaimedCommitmentFee(participantAddress)
                .orElse(BigInteger.ZERO);

        GetDashboardPStatistic statistic = new GetDashboardPStatistic(
                BigInteger.ZERO, //Gak relevan,
                totalCommitmentFee.subtract(totalClaimedCommitmentFee),
                totalClaimedCommitmentFee
        );

        List<GetDashboardPUpcomingSession> upcomingSessions = enrollRepository.upcomingSessionByEnrolled(participantAddress)
                .stream().map(session -> {

                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);

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

        List<GetDashboardPCompletedSession> completeSession = enrollRepository.completedSessionByEnrolled(participantAddress)
                .stream().map(session -> {
                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);
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

        GetDashboardP getDashboardP = new GetDashboardP(
                statistic,
                upcomingSessions,
                completeSession
        );

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

        List<GetDashboardEOUpcomingSession> upcomingSession = eventRepository.upcomingSessionByOrganizer(organizerAddress)
                .stream().map(session -> {

                            Event event = eventRepository.findById(session.getId())
                                    .orElse(null);

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

        List<GetDashboardEOCompletedSession> completededSession = eventRepository.completedSessionByOrganizer(organizerAddress)
                .stream().map(session -> {

                    Event event = eventRepository.findById(session.getId())
                            .orElse(null);

                    return new GetDashboardEOCompletedSession(
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

        GetDashboardEO getDashboardEO = new GetDashboardEO(
                null,
                upcomingSession,
                completededSession
        );

        return ResponseEntity.ok(getDashboardEO);
    }

}
