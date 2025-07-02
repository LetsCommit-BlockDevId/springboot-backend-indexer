package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.OrganizerClaimHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EventRepository eventRepository;
    private final EnrollRepository enrollRepository;
    private final OrganizerClaimHistoryRepository organizerClaimHistoryRepository;
    private final AttendRepository attendRepository;

    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());


    /*
     * ======================================================================================
     *                     Participant Statistic
     * ======================================================================================
     * */
    public GetDashboardPStatistic getParticipantStatistic(String participantAddress)
            throws ResponseStatusException {

        BigInteger totalCommitmentFee = enrollRepository
                .totalCommitmentFee(participantAddress)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "participant don't have enrolled event"
                ));

        BigInteger totalCommitmentFeeClaimed = attendRepository
                .totalClaimedCommitmentFee(participantAddress)
                .orElse(BigInteger.ZERO);

        // Commitment yang belum diklaim = tot commitment fee - claimed commitment fee
        BigInteger unclaimedCommitmentFee = totalCommitmentFee.subtract(totalCommitmentFeeClaimed);

        return new GetDashboardPStatistic(
                null, //Gak relevan,
                unclaimedCommitmentFee,
                totalCommitmentFeeClaimed
        );
    }

    /*
     * ======================================================================================
     *                     Participant Upcoming Session
     * ======================================================================================
     * */
    private Function<Session, GetDashboardPUpcomingSession>
    sessionToUpcomingSessionParticipant() {

        return session -> {

            Event event = eventRepository
                    .findById(session.getId())
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
        };
    }

    public List<GetDashboardPUpcomingSession>
    getParticipantUpcomingSession(String participantAddress) {
        return enrollRepository
                .upcomingSessionByEnrolled(participantAddress)
                .stream()
                .map(sessionToUpcomingSessionParticipant())
                .toList();
    }

    /*
     * ======================================================================================
     *                     Participant Completed Session
     * ======================================================================================
     * */
    private Function<Session, GetDashboardPCompletedSession>
    sessionToCompletedSessionParticipant() {
        return session -> {

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
        };
    }

    public List<GetDashboardPCompletedSession> getParticipantCompletedSession(String participantAddress) {
        return enrollRepository
                .completedSessionByEnrolled(participantAddress)
                .stream()
                .map(sessionToCompletedSessionParticipant())
                .toList();
    }

    /*
     * ======================================================================================
     *                     Organizer Statistic
     * ======================================================================================
     * */
    public GetDashboardEOStatistic getOrganizerStatistic(String organizerAddress) {
        BigInteger totalRevenue = eventRepository.totalRevenueOfOrganizer(organizerAddress);
        BigInteger totalClaimedRevenue = organizerClaimHistoryRepository.totalClaimedRevenue(organizerAddress);
        BigInteger availableWithdraw = totalRevenue.subtract(totalClaimedRevenue); // Commitment yang belum diklaim = tot commitment fee - claimed commitment fee
        return new GetDashboardEOStatistic(totalRevenue, availableWithdraw, totalClaimedRevenue);
    }

    /*
     * ======================================================================================
     *                     Organizer Upcoming Session
     * ======================================================================================
     * */
    private Function<Session, GetDashboardEOUpcomingSession>
    sessionToUpcomingSessionOrganizer() {

        return session -> {

            Event event = eventRepository
                    .findById(session.getId())
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
        };

    }

    public List<GetDashboardEOUpcomingSession> getOrganizerUpcomingSession(String organizerAddress) {
        return eventRepository
                .upcomingSessionByOrganizer(organizerAddress)
                .stream()
                .map(sessionToUpcomingSessionOrganizer())
                .toList();
    }

    /*
     * ======================================================================================
     *                     Organizer Completed Session
     * ======================================================================================
     * */
    private Function<Session, GetDashboardEOCompletedSession>
    sessionToCompletedSessionOrganizer() {

        return session -> {

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


        };
    }

    public List<GetDashboardEOCompletedSession> getOrganizerCompletedSession(String organizerAddress) {
        return eventRepository.completedSessionByOrganizer(organizerAddress)
                .stream().map(sessionToCompletedSessionOrganizer())
                .toList();
    }


}
