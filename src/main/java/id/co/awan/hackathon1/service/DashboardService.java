package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Attend;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.OrganizerClaimHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
     *                          Common Services
     * ======================================================================================
     * */

    public GetDashboardEOStatistic
    getOrganizerStatistic(String organizerAddress) {

        BigInteger totalRevenue = eventRepository.totalRevenueOfOrganizer(organizerAddress)
                .orElse(BigInteger.ZERO);
        BigInteger totalClaimedRevenue = organizerClaimHistoryRepository.totalClaimedRevenue(organizerAddress)
                .orElse(BigInteger.ZERO);

        BigInteger availableWithdraw = totalRevenue.subtract(totalClaimedRevenue); // Commitment yang belum diklaim = tot commitment fee - claimed commitment fee
        return new GetDashboardEOStatistic(totalRevenue, availableWithdraw, totalClaimedRevenue);
    }

    public GetDashboardPStatistic
    getParticipantStatistic(String participantAddress) {

        BigInteger totalCommitmentFee = enrollRepository
                .totalCommitmentFee(participantAddress)
                .orElse(BigInteger.ZERO);

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

    public List<GetDashboardEOUpcomingSession>
    getOrganizerUpcomingSession(String organizerAddress) {
        return eventRepository
                .upcomingSessionByOrganizer(organizerAddress)
                .stream()
                .map(sessionToUpcomingSessionOrganizer())
                .toList();
    }

    public List<GetDashboardPUpcomingSession>
    getParticipantUpcomingSession(String participantAddress) {
        return enrollRepository
                .upcomingSessionByEnrolled(participantAddress)
                .stream()
                .map(sessionToUpcomingSessionParticipant())
                .toList();
    }

    public List<GetDashboardEOCompletedSession>
    getOrganizerCompletedSession(String organizerAddress) {
        return eventRepository
                .completedSessionByOrganizer(organizerAddress)
                .stream()
                .map(sessionToCompletedSessionOrganizer())
                .toList();
    }

    public List<GetDashboardPCompletedSession>
    getParticipantCompletedSession(String participantAddress) {
        return enrollRepository
                .completedSessionByEnrolled(participantAddress)
                .stream()
                .map(sessionToCompletedSessionParticipant(participantAddress))
                .toList();
    }

    /*
     * ======================================================================================
     *                             DTO / Stream Mapper
     * ======================================================================================
     * */

    private Function<Session, GetDashboardPUpcomingSession>
    sessionToUpcomingSessionParticipant() {

        return session -> {

            Event event = getEventBySession(session);

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

    private Function<Session, GetDashboardPCompletedSession>
    sessionToCompletedSessionParticipant(String participantAddress) {
        return session -> {

            Event event = getEventBySession(session);

            if (event == null) {
                return null;
            }

            Boolean isAttended = isParticipantHasAttendedSession(participantAddress, session);

            return new GetDashboardPCompletedSession(
                    event.getId(),
                    event.getTitle(),
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(session.getStartSessionTime().longValue())),
                    humanReadableFormatter.format(Instant.ofEpochSecond(session.getEndSessionTime().longValue())),
                    isAttended
            );
        };
    }

    private Function<Session, GetDashboardEOUpcomingSession>
    sessionToUpcomingSessionOrganizer() {

        return session -> {

            Event event = getEventBySession(session);

            if (event == null) {
                return null;
            }

            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();
            long durationInSeconds = endSessionTime - startSessionTime;

            Integer totalParticipant = enrollRepository.countAllById(event.getId());


            return new GetDashboardEOUpcomingSession(
                    event.getId(),
                    event.getTitle(),
                    totalParticipant,
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

    private Function<Session, GetDashboardEOCompletedSession>
    sessionToCompletedSessionOrganizer() {

        return session -> {

            Event event = getEventBySession(session);

            if (event == null) {
                return null;
            }

            Integer attendanceCount = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());
            Integer totalParticipant = enrollRepository.countAllById(event.getId());

            return new GetDashboardEOCompletedSession(
                    event.getId(),
                    event.getTitle(),
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(session.getStartSessionTime().longValue())),
                    humanReadableFormatter.format(Instant.ofEpochSecond(session.getEndSessionTime().longValue())),
                    attendanceCount,
                    totalParticipant,
                    // TODO: Complete this logic
                    null
            );


        };
    }

    /*
     * ======================================================================================
     *                             Pure JPA
     * ======================================================================================
     * */

    public Event getEventBySession(Session session) {
        return eventRepository
                .findById(session.getId())
                .orElse(null);
    }

    private Boolean isParticipantHasAttendedSession(String participantAddress, Session session) {
        return attendRepository.existsById(new Attend.AttendId(
                session.getId(),
                session.getSession(),
                participantAddress
        ));
    }


}
