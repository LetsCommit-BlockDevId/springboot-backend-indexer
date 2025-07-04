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

    private final DecimalFormat usdDecimalFormatter = new DecimalFormat("0.000000");
    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());


    /*
     * ======================================================================================
     *                                  Common Service
     * ======================================================================================
     * */

    public Event
    getEventById(BigInteger eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event Id not found!"
                ));
    }

    public List<GetEventDetailEOSession>
    getSessionsOrganizerView(Event event) {

        List<Session> sessions = sessionRepository.findAllById(event.getId());
        return sessions.stream()
                .map(sessionToEventDetailEOSession())
                .toList();

    }

    public List<GetEventDetailPSession>
    getSessionsParticipantView(Event event, String participantAddress) {
        List<Session> sessions = sessionRepository.findAllById(event.getId());
        return sessions
                .stream()
                .map(sessionToEventDetailPSession(participantAddress))
                .toList();
    }

    public List<GetEventDetailPSession>
    getSessionsGuestView(Event event) {
        List<Session> sessions = sessionRepository.findAllById(event.getId());
        return sessions
                .stream()
                .map(sessionToGuestEventDetailPSession())
                .toList();
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

    public GetEventDetailPStatistic
    getEventDetailPStatistic(Event event, String participantAddress, List<GetEventDetailPSession> session) {

        Integer totalAttendInAnEvent = getTotalParticipantAttendInAnEvent(event, participantAddress);
        return new GetEventDetailPStatistic(
                totalAttendInAnEvent,
                session.size(),
                null
        );
    }


    private String formatDurationFromNow(BigInteger diff) {

        if (diff.compareTo(BigInteger.ZERO) <= 0) return "Sudah lewat";

        BigInteger[] hourAndRemainder = diff.divideAndRemainder(BigInteger.valueOf(3600));
        BigInteger hours = hourAndRemainder[0];
        BigInteger[] minuteAndRemainder = hourAndRemainder[1].divideAndRemainder(BigInteger.valueOf(60));
        BigInteger minutes = minuteAndRemainder[0];

        StringBuilder sb = new StringBuilder();
        if (hours.compareTo(BigInteger.ZERO) > 0) sb.append(hours).append(" jam ");
        if (minutes.compareTo(BigInteger.ZERO) > 0) sb.append(minutes).append(" menit");
        if (sb.isEmpty()) sb.append("Kurang dari 1 menit");
        return sb.toString().trim();
    }

    /*
     * ======================================================================================
     *                           Validation
     * ======================================================================================
     * */

    public void
    validateParticipantExistInEvent(BigInteger eventId, String participantAddress) throws ResponseStatusException {
        if (!enrollRepository.existsById(new Enroll.EnrollId(eventId, participantAddress))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not exists!");
        }
    }

    /*
     * ======================================================================================
     *                             Pure JPA
     * ======================================================================================
     * */

    public Integer
    getTotalParticipant(Event event) {
        return enrollRepository.countAllById(event.getId());
    }

    public Integer
    getTotalParticipantAttendInAnEvent(Event event, String participantAddress) {
        return attendRepository.countAllByIdAndParticipant(event.getId(), participantAddress);
    }

    public List<String>
    getEventParticipants(Event event) {
        return enrollRepository.findAllParticipanInAnEvent(event.getId());
    }

    public Boolean
    isParticipantExistInEvent(BigInteger eventId, String participantAddress) {
        return enrollRepository.existsById(new Enroll.EnrollId(eventId, participantAddress));
    }

    /*
     * ======================================================================================
     *                             Status Decision
     * ======================================================================================
     * */

    private SessionStatus
    getSessionStatusOrganizerView(Session session) {
        SessionStatus status;

        long currentTime = Instant.now().getEpochSecond();
        long startSessionTime = session.getStartSessionTime().longValue();
        long endSessionTime = session.getEndSessionTime().longValue();

        // Session Status Decision
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
        return status;
    }

    private SessionStatus
    getSessionStatusParticipantView(String participantAddress, Session session) {
        SessionStatus status;

        long startSessionTime = session.getStartSessionTime().longValue();
        long endSessionTime = session.getEndSessionTime().longValue();
        long currentTime = Instant.now().getEpochSecond();

        var attendId = new Attend.AttendId(session.getId(), session.getSession(), participantAddress);
        Attend participantAttendData = attendRepository.findById(attendId)
                .orElse(null);

        // Session Status Decision
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
        return status;
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

    /*
     * ======================================================================================
     *                             DTO / Stream Mapper
     * ======================================================================================
     * */

    public Function<Event, GetEvent>
    eventToGetEventDTO(EventState status) {
        return event -> {

            BigInteger eventId = event.getId();
            List<String> participantList = getEventParticipants(event);

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
                    participantList,
                    participantList.size(),
                    event.getMaxParticipant(),
                    status
            );
        };
    }

    private Function<Session, GetEventDetailEOSession>
    sessionToEventDetailEOSession() {

        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());

            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();
            long durationInSeconds = endSessionTime - startSessionTime;
            BigInteger estimatedInSecond = BigInteger.valueOf(startSessionTime - Instant.now().getEpochSecond());

            SessionStatus status = getSessionStatusOrganizerView(session);

            // QR Button
            boolean isQrActive = status.equals(SessionStatus.RUNNING) && (session.getAttendToken() == null);

            return new GetEventDetailEOSession(
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                    estimatedInSecond,
                    formatDurationFromNow(estimatedInSecond),
                    Math.floorDiv((int) durationInSeconds, 3600),
                    Math.floorDiv((int) (durationInSeconds % 3600), 60),
                    totalAttenders,
                    status,
                    isQrActive
            );

        };
    }

    private Function<Session, GetEventDetailPSession>
    sessionToEventDetailPSession(String participantAddress) {
        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());

            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();
            BigInteger estimatedInSecond = BigInteger.valueOf(startSessionTime - Instant.now().getEpochSecond());

            SessionStatus status = getSessionStatusParticipantView(participantAddress, session);

            long durationInSeconds = endSessionTime - startSessionTime;

            return new GetEventDetailPSession(
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                    estimatedInSecond,
                    formatDurationFromNow(estimatedInSecond),
                    Math.floorDiv((int) durationInSeconds, 3600),
                    Math.floorDiv((int) (durationInSeconds % 3600), 60),
                    totalAttenders,
                    status
            );

        };
    }

    private Function<Session, GetEventDetailPSession>
    sessionToGuestEventDetailPSession() {
        return session -> {

            Integer totalAttenders = attendRepository.countAllByIdAndSession(session.getId(), session.getSession());

            long startSessionTime = session.getStartSessionTime().longValue();
            long endSessionTime = session.getEndSessionTime().longValue();
            BigInteger estimatedInSecond = BigInteger.valueOf(startSessionTime - Instant.now().getEpochSecond());

            long durationInSeconds = endSessionTime - startSessionTime;

            return new GetEventDetailPSession(
                    session.getSession(),
                    session.getTitle(),
                    session.getStartSessionTime(),
                    session.getEndSessionTime(),
                    humanReadableFormatter.format(Instant.ofEpochSecond(startSessionTime)),
                    humanReadableFormatter.format(Instant.ofEpochSecond(endSessionTime)),
                    estimatedInSecond,
                    formatDurationFromNow(estimatedInSecond),
                    Math.floorDiv((int) durationInSeconds, 3600),
                    Math.floorDiv((int) (durationInSeconds % 3600), 60),
                    totalAttenders,
                    null
            );

        };
    }

    /*
     * ======================================================================================
     *                             Stream Filter
     * ======================================================================================
     * */

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

}

