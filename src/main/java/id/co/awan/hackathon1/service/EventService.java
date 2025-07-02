package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Attend;
import id.co.awan.hackathon1.model.entity.Enroll;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class EventService {

    private final AttendRepository attendRepository;
    private final EnrollRepository enrollRepository;
    private final SessionRepository sessionRepository;
    private final EventRepository eventRepository;

    DecimalFormat usdDecimalFormatter = new DecimalFormat("0.000000");

    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());


    /*
     * ======================================================================================
     *                                  Get All Event Section
     * ======================================================================================
     * */

    public Event
    findEventById(BigInteger eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event Id not found!"
                ));
    }

    public Predicate<Event>
    filterEventByState(EventState eventState) {
        return event -> {

            long currentTime = Instant.now().getEpochSecond();
            long endSaleTime = event.getEndSaleDate().longValue();

            Optional<Boolean> lastEventSessionStatus = sessionRepository
                    .isLastEventSessionHasEnd(event.getId());

            return lastEventSessionStatus
                    .map(lastEventSessionHasEnded ->
                            switch (eventState) {
                                case FINISHED -> lastEventSessionHasEnded.equals(true); // last session dilakukan
                                case ON_GOING ->
                                        endSaleTime <= currentTime && lastEventSessionHasEnded.equals(false); // waktu akhir penjualan terlampaui waktu sekarang
                                case ON_SALE ->
                                        endSaleTime > currentTime; // waktu akhir penjualan masih melampaui sekarang
                            })
                    .orElseGet(() -> endSaleTime > currentTime); // ON_SALE
        };
    }

    public Function<Event, GetEvent>
    eventToGetEventDTO(EventState status) {
        return event -> {

            BigInteger eventId = event.getId();
            Integer participant = enrollRepository.countAllById(eventId);

            BigInteger priceAmount = event.getPriceAmount();
            BigInteger commitmentAmount = event.getCommitmentAmount();
            BigInteger totalAmount = priceAmount.add(commitmentAmount);

            BigDecimal priceAmountUsdFormat = new BigDecimal(priceAmount).movePointLeft(6);
            BigDecimal commitmentAmountUsdFormat = new BigDecimal(commitmentAmount).movePointLeft(6);
            BigDecimal totalAmountUsdFormat = new BigDecimal(totalAmount).movePointLeft(6);

            Instant startSaleDate = Instant.ofEpochSecond(event.getStartSaleDate().longValue());
            Instant endSaleDate = Instant.ofEpochSecond(event.getEndSaleDate().longValue());

            String startSaleDateHumanReadable = humanReadableFormatter.format(startSaleDate);
            String endSaleDateHumanReadable = humanReadableFormatter.format(endSaleDate);

            return new GetEvent(
                    eventId,
                    event.getTitle(),
                    event.getDescription(),
                    event.getImageUri(),
                    priceAmount,
                    commitmentAmount,
                    totalAmount,
                    usdDecimalFormatter.format(priceAmountUsdFormat),
                    usdDecimalFormatter.format(commitmentAmountUsdFormat),
                    usdDecimalFormatter.format(totalAmountUsdFormat),
                    event.getStartSaleDate(),
                    event.getEndSaleDate(),
                    startSaleDateHumanReadable,
                    endSaleDateHumanReadable,
                    event.getOrganizer(),
                    event.getLocation(),
                    participant,
                    event.getMaxParticipant(),
                    status
            );
        };
    }

    public Integer
    getTotalParticipant(Event event) {
        return enrollRepository.countAllById(event.getId());
    }

    /*
     * ======================================================================================
     *                             Get Detail Event Organizer View
     * ======================================================================================
     * */

    private Function<Session, GetEventDetailEOSession>
    sessionToEventDetailEOSession() {

        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());

            long currentTime = Instant.now().getEpochSecond();
            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();
            long durationInSeconds = endSessionTime - startSessionTime;

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


            // QR Button
            boolean isQrActive = status.equals(SessionStatus.RUNNING) && (session.getAttendToken() == null);

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
                    isQrActive
            );

        };
    }

    public List<GetEventDetailEOSession>
    getSessionsOrganizerView(Event event) {

        List<Session> sessions = sessionRepository.findAllById(event.getId());
        return sessions.stream()
                .map(sessionToEventDetailEOSession())
                .toList();

    }

    /*
     * ======================================================================================
     *                             Get Detail Event Participant View
     * ======================================================================================
     * */


    public void
    validateParticipantExistInEvent(BigInteger eventId, String participantAddress) throws ResponseStatusException {
        if (!enrollRepository.existsById(new Enroll.EnrollId(eventId, participantAddress))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not exists!");
        }
    }

    public EventState
    getEventStatus(Event event) {
        long currentTime = Instant.now().getEpochSecond();
        long endSaleTime = event.getEndSaleDate().longValue();

        Boolean isLastEventSessionEnd = sessionRepository.isLastEventSessionHasEnd(event.getId())
                .orElse(false);

        if (isLastEventSessionEnd) {
            return EventState.FINISHED;
        } else if (currentTime > endSaleTime) {
            return EventState.ON_GOING;
        } else {
            return EventState.ON_SALE;
        }
    }

    public GetEventDetailEOStatistic
    getEventDetailEOStatistic(Event event, Integer totalParticipant, Integer sessionLength) {

        Integer attendantCounterInAnEvent = attendRepository.countAllById(event.getId());

        // sesi yang memiliki token adalah sesi yang completed
        List<Session> sessions = sessionRepository.findAllById(event.getId());
        long sessionCompletedCount = sessions.stream()
                .filter(session -> session.getAttendToken() != null)
                .count();

        BigInteger eventPrice = event.getPriceAmount();

        return new GetEventDetailEOStatistic(
                // price * participant
                eventPrice.multiply(BigInteger.valueOf(totalParticipant)),
                // sesi yang memiliki link
                (int) sessionCompletedCount,
                // total sesi
                sessionLength,
                // rata-rata persenan kehadiran = total semua kehadiran * 100 / total sesi
                (attendantCounterInAnEvent * 100) / sessionLength
        );
    }

    private Function<Session, GetEventDetailPSession>
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

    public List<GetEventDetailPSession>
    getSessionsParticipantView(Event event, String participantAddress) {
        List<Session> sessions = sessionRepository.findAllById(event.getId());
        return sessions
                .stream()
                .map(sessionToEventDetailPSession(participantAddress))
                .toList();
    }

    public Integer
    getTotalParticipantAttendInAnEvent(Event event, String participantAddress) {
        return attendRepository.countAllByIdAndParticipant(event.getId(), participantAddress);
    }

    public GetEventDetailPStatistic
    getEventDetailPStatistic(Integer totalAttendInAnEvent, List<GetEventDetailPSession> session) {
        return new GetEventDetailPStatistic(
                totalAttendInAnEvent,
                session.size(),
                null
        );
    }

}

