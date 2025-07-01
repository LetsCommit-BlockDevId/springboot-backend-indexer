package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Attend;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class EventService {

    private final AttendRepository attendRepository;
    private final EnrollRepository enrollRepository;
    private final SessionRepository sessionRepository;

    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());


    public Predicate<Event>
    filterEventByState(EventState eventState) {
        return event -> {

            // Mendapatkan waktu dari sesi terakir pada event
            long lastSessionTime = sessionRepository.findLatestSessionTimeOfEvent(event.getId())
                    .orElse(BigInteger.ZERO)
                    .longValue();

            long currentTime = Instant.now().getEpochSecond();
            long endSaleTime = event.getEndSaleDate().longValue();

            return switch (eventState) {
                case FINISHED -> lastSessionTime < currentTime; // waktu akhir sesi terlampaui waktu sekarang
                case ON_GOING -> endSaleTime <= currentTime; // waktu akhir penjualan terlampaui waktu sekarang
                case ON_SALE -> endSaleTime > currentTime; // waktu akhir penjualan masih melampaui sekarang
            };
        };
    }

    public Function<Event, GetEvent>
    eventToGetEventDTO(EventState eventState) {
        return event -> {

            long startSaleDate = event.getStartSaleDate().longValue();
            long endSaleDate = event.getEndSaleDate().longValue();

            Integer participant = enrollRepository.countAllById(event.getId());

            return new GetEvent(
                    event.getId(),
                    event.getTitle(),
                    event.getDescription(),
                    event.getImageUri(),
                    event.getPriceAmount(),
                    event.getCommitmentAmount(),
                    event.getPriceAmount().add(event.getCommitmentAmount()),
                    event.getStartSaleDate(),
                    event.getEndSaleDate(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSaleDate)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSaleDate)),
                    event.getOrganizer(),
                    event.getLocation(),
                    participant,
                    event.getMaxParticipant(),
                    eventState
            );
        };
    }

    public Function<Session, GetEventDetailEOSession>
    sessionToEventDetailEOSession() {

        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());

            long currentTime = Instant.now().getEpochSecond();
            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();

            // Session Status Decision
            SessionStatus status;
            if (currentTime > startSessionTime && currentTime < endSessionTime) {

                // Sesi Sedang Berlangsung
                status = SessionStatus.RUNNING;
            } else if (currentTime > endSessionTime) { // Sesi sudah selesai

                // Cek sesi dihadiri atau tidak
                if (session.getAttendToken() == null) {
                    status = SessionStatus.UNATENDED;
                } else {
                    status = SessionStatus.COMPLETE;
                }
            } else {

                // Sesi Belum Berjalan
                status = SessionStatus.UPCOMING;
            }

            long durationInSeconds = endSessionTime - startSessionTime;

            return new GetEventDetailEOSession(
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                    Math.floorDiv((int) durationInSeconds, 3600),
                    Math.floorDiv((int) (durationInSeconds % 3600), 60),
                    totalAttenders,
                    status,
                    status.equals(SessionStatus.RUNNING)
            );

        };
    }

    public Function<Session, GetEventDetailPSession>
    sessionToEventDetailPSession(String participantAddress) {
        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());
            Attend participantAttendData = attendRepository.findById(new Attend.AttendId(session.getId(), session.getSession(), participantAddress))
                    .orElse(null);

            long currentTime = Instant.now().getEpochSecond();
            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();

            // Session Status Decision
            SessionStatus status;
            if (currentTime > startSessionTime && currentTime < endSessionTime) {

                // Sesi Sedang Berlangsung
                status = SessionStatus.RUNNING;
            } else if (currentTime > endSessionTime) { // Sesi sudah selesai

                // Cek sesi dihadiri atau tidak
                if (participantAttendData == null) {
                    status = SessionStatus.UNATENDED;
                } else {
                    status = SessionStatus.COMPLETE;
                }
            } else {

                // Sesi Belum Berjalan
                status = SessionStatus.UPCOMING;
            }

            long durationInSeconds = endSessionTime - startSessionTime;

            return new GetEventDetailPSession(
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                    Math.floorDiv((int) durationInSeconds, 3600),
                    Math.floorDiv((int) (durationInSeconds % 3600), 60),
                    totalAttenders,
                    status
            );

        };
    }

    public EventState
    getEventStateStatus(BigInteger eventId, Event event) {
        long currentTime = Instant.now().getEpochSecond();
        long endSaleTime = event.getEndSaleDate().longValue();

        Boolean isLastEventSessionEnd = sessionRepository.isLastEventSessionHasEnd(eventId)
                .orElse(false);

        if (isLastEventSessionEnd) {
            return EventState.FINISHED;
        } else if (currentTime > endSaleTime) {
            return EventState.ON_GOING;
        } else {
            return EventState.ON_SALE;
        }
    }
}

