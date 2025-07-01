package id.co.awan.hackathon1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final EnrollRepository enrollRepository;
    private final SessionRepository sessionRepository;
    private final AttendRepository attendRepository;
    private final ObjectMapper objectMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    // DONE
    @Operation(
            summary = "Mendapatkan data event berdasarkan state"
    )
    @GetMapping
    public ResponseEntity<List<GetEvent>> getEvents(
            @RequestParam(name = "state")
            EventState eventState
    ) {

        List<GetEvent> events = eventRepository.findAll()
                .stream()
                .filter(event -> {

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
                })
                .map(event -> {

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
                            formatter.format(Instant.ofEpochSecond(startSaleDate)),
                            formatter.format(Instant.ofEpochSecond(endSaleDate)),
                            event.getOrganizer(),
                            event.getLocation(),
                            participant,
                            event.getMaxParticipant(),
                            eventState
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }

    @Operation(
            summary = "Data event pada participant view"
    )
    @GetMapping(path = "{eventId}")
    public ResponseEntity<GetEventDetailEO> getEventDetailViewEventOrganizer(
            @PathVariable(name = "eventId")
            BigInteger eventId
    ) {

        Event event = eventRepository.findById(eventId)
                .orElse(null);

        Integer totalParticipant = enrollRepository.countAllById(eventId);

        List<GetEventDetailEOSession> getEventDetailEOSessions = sessionRepository.findAllById(eventId)
                .stream()
                .map(session -> {

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

                    long durationInSeconds = endSessionTime-startSessionTime;

                    return new GetEventDetailEOSession(
                            session.getSession(),
                            session.getTitle(),
                            session.getStartSessionTime(),
                            session.getEndSessionTime(),
                            formatter.format(Instant.ofEpochSecond(startSessionTime)),
                            formatter.format(Instant.ofEpochSecond(endSessionTime)),
                            Math.floorDiv((int) durationInSeconds, 3600),
                            Math.floorDiv((int) (durationInSeconds % 3600), 60),
                            totalAttenders,
                            status,
                            status.equals(SessionStatus.RUNNING)
                    );

                })
                .toList();

        EventState status;
        long currentTime = Instant.now().getEpochSecond();
        long endSaleTime = event.getEndSaleDate().longValue();

        Boolean isLastEventSessionEnd = sessionRepository.isLastEventSessionHasEnd(eventId)
                .orElse(false);

        if (isLastEventSessionEnd) {
            status = EventState.FINISHED;
        } else if (currentTime > endSaleTime) {
            status = EventState.ON_GOING;
        } else {
            status = EventState.ON_SALE;
        }

        GetEventDetailEO getEventDetailEO = new GetEventDetailEO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getImageUri(),
                event.getPriceAmount(),
                event.getCommitmentAmount(),
                event.getPriceAmount().add(event.getCommitmentAmount()),
                event.getStartSaleDate(),
                event.getEndSaleDate(),
                event.getOrganizer(),
                event.getLocation(),
                totalParticipant,
                event.getMaxParticipant(),
                status,
                isLastEventSessionEnd,
                getEventDetailEOSessions,
                null
        );

        return ResponseEntity.ok(getEventDetailEO);
    }

    @Operation(
            summary = "Data event pada event participant view"
    )
    @GetMapping(path = "{eventId}/{participantAddress}")
    public ResponseEntity<GetEventDetailP> getEventDetailViewParticipant(
            @PathVariable(name = "eventId")
            BigInteger eventId,

            @PathVariable(name = "participantAddress")
            String participantAddress
    ) {
        try {
            GetEventDetailP getEventDetailP = objectMapper.readValue("""
                        {
                        "eventId": 0,
                        "title": "DeFi Fundamental Mastercllass",
                        "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
                        "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
                        "priceAmount": 60,
                        "commitmentAmount": 50,
                        "totalAmount": 110,
                        "startSaleDate": 1751295986,
                        "endSaleDate": 1751299986,
                        "organizer": "string",
                        "location": "string",
                        "participant": 10,
                        "maxParticipant": 20,
                        "status": "ON_GOING",
                        "session": [
                          {
                            "sessionNumber": 0,
                            "title": "DeFi Fundamental Mastercllass",
                            "startSession": 1751295986,
                            "endSession": 1751295986,
                            "durationInHours": 1,
                            "durationInMinute": 60,
                            "peopleAttend": 0,
                            "status": "UPCOMING"
                          }
                        ],
                        "statistic": {
                          "sessionAttend": 1,
                          "sessionLength": 5,
                          "availableCommitment": 10
                        }
                      }
                    """, new TypeReference<GetEventDetailP>() {
            });

            return ResponseEntity.ok(getEventDetailP);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


//    @Operation(
//            summary = "Mendapatkan detail dari event"
////            description = "Ini Deskripsi"
////            responses = {
////                    @ApiResponse(
////                            description = "Successful Operation",
////                            responseCode = "200",
////                            content = @Content(
////                                    mediaType = "application/json",
////                                    schema = @Schema(implementation = GetEventResponse.class)
////                            )
////                    )
////            }
//    )
//    @GetMapping(path = "{eventId}")
//    public ResponseEntity<List<GetEvent>> getEventDetail(
//            @PathVariable(name = "eventId")
//            @Parameter(description = "Berisi Event ID")
//            BigInteger eventId
//    ) {
//
//
//        try {
//            List<GetEvent> getEvents = objectMapper.readValue("""
//                      [{
//                        "eventId": 0,
//                        "title": "DeFi Fundamental Mastercllass",
//                        "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
//                        "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
//                        "priceAmount": 60,
//                        "commitmentAmount": 50,
//                        "totalAmount": 110,
//                        "startSaleDate": 1751295986,
//                        "endSaleDate": 1751299986,
//                        "organizer": "string",
//                        "location": "string",
//                        "participant": 10,
//                        "maxParticipant": 20,
//                        "status": "ON_SALE",
//                        "hasRegist": true
//                      }]
//                    """, new TypeReference<List<GetEvent>>() {
//            });
//
//            return ResponseEntity.ok(getEvents);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

//    }


//    @Operation(
//            summary = "Mendapatkan data event berdasarkan organizer"
//    )
//    @GetMapping(path = "{organizerAddress}")
//    public ResponseEntity<List<Event>> getEventByOrganizer(
//            @PathVariable(name = "organizerAddress")
//            String organizerAddress
//    ) {
//
//        List<Event> collect = eventRepository.findAll()
//                .stream()
//                .filter(event -> event.getOrganizer().equals(organizerAddress))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(collect);
//    }


//    @Operation(
//            summary = "Mendapatkan data event berdasarkan state"
//    )
//    @GetMapping
//    public ResponseEntity<List<GetEvent>> getEvents(
//            @RequestParam(name = "state")
//            EventState eventState
//    ) {
//
//        List<GetEvent> events = eventRepository.findAll()
//                .stream()
//                .filter(event -> {
//
//                    if (eventState.equals(EventState.FINISHED)) {
//
//                        return true;
//                    } else if (eventState.equals(EventState.ON_GOING)) {
//                        // On Going
//                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
//                                .isBefore(Instant.now());
//
//                    } else {
//                        // On Sale
//                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
//                                .isAfter(Instant.now());
//                    }
//                })
//                .map(EventMapper.INSTANCE::toDTO).collect(Collectors.toList());
//
//        return ResponseEntity.ok(events);
//    }

}
