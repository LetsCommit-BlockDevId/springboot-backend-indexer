package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Attend;
import id.co.awan.hackathon1.model.entity.Enroll;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import id.co.awan.hackathon1.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final EventRepository eventRepository;
    private final EnrollRepository enrollRepository;
    private final AttendRepository attendRepository;
    private final SessionRepository sessionRepository;

    // DONE
    @Operation(
            summary = "Mendapatkan data event berdasarkan state"
    )
    @GetMapping
    public ResponseEntity<List<GetEvent>>
    getEvents(
            @RequestParam(name = "state")
            EventState eventState
    ) {

        List<GetEvent> events = eventRepository.findAll()
                .stream()
                .filter(eventService.filterEventByState(eventState))
                .map(eventService.eventToGetEventDTO(eventState))
                .collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }


    @Operation(
            summary = "Data event pada organizer view"
    )
    @GetMapping(path = "{eventId}")
    public ResponseEntity<GetEventDetailEO>
    getEventDetailViewEventOrganizer(
            @PathVariable(name = "eventId")
            BigInteger eventId
    ) {

        /*
         * ======================================================================================
         *                     Validation Field
         * ======================================================================================
         * */
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event Id not found!"
                ));


        /*
         * ======================================================================================
         *                     Finding Sub Field GetEventDetailEO
         * ======================================================================================
         * */
        Integer totalParticipant = enrollRepository.countAllById(eventId);
        List<Session> sessions = sessionRepository.findAllById(eventId);
        List<GetEventDetailEOSession> getEventDetailEOSessions = sessions.stream()
                .map(eventService.sessionToEventDetailEOSession())
                .toList();

        long sessionHasActivatedLink = sessions.stream().filter(session -> session.getAttendToken() != null)
                .count();

        EventState status = eventService.getEventStateStatus(eventId, event);
        Integer totalAttendedInAnEvent = attendRepository.countAllById(eventId);

        GetEventDetailEOStatistic statistic = new GetEventDetailEOStatistic(
                // price * participant
                event.getPriceAmount().multiply(BigInteger.valueOf(totalParticipant)),
                // sesi yang memiliki link
                (int) sessionHasActivatedLink,
                // total sesi
                getEventDetailEOSessions.size(),
                // rata-rata persenan kehadiran = total semua kehadiran * 100 / total sesi
                (totalAttendedInAnEvent * 100) / getEventDetailEOSessions.size()
        );

        /*
         * ======================================================================================
         *                      Construct Response GetEventDetailEO
         * ======================================================================================
         * */
        GetEventDetailEO getEventDetailEO = new GetEventDetailEO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getImageUri(),
                event.getPriceAmount(),
                event.getCommitmentAmount(),
                // SUM Operation
                event.getPriceAmount().add(event.getCommitmentAmount()),
                event.getStartSaleDate(),
                event.getEndSaleDate(),
                event.getOrganizer(),
                event.getLocation(),
                totalParticipant,
                event.getMaxParticipant(),
                status,
                // canWithDrawStatus only when event state finished
                status.equals(EventState.FINISHED),
                getEventDetailEOSessions,
                statistic
        );

        return ResponseEntity.ok(getEventDetailEO);
    }

    @Operation(
            summary = "Data event pada event participant view"
    )
    @GetMapping(path = "{eventId}/{participantAddress}")
    public ResponseEntity<GetEventDetailP>
    getEventDetailViewParticipant(
            @PathVariable(name = "eventId")
            BigInteger eventId,

            @PathVariable(name = "participantAddress")
            String participantAddress
    ) {

        /*
         * ======================================================================================
         *                     Validation Field
         * ======================================================================================
         * */
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event Id not found!"
                ));

        if (!enrollRepository.existsById(new Enroll.EnrollId(eventId, participantAddress))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not exists!");
        }

        /*
         * ======================================================================================
         *                     Finding Sub Field GetEventDetailP
         * ======================================================================================
         * */
        List<Session> sessions = sessionRepository.findAllById(eventId);
        List<GetEventDetailPSession> session = sessions
                .stream()
                .map(eventService.sessionToEventDetailPSession(participantAddress))
                .toList();

        Integer totalParticipant = enrollRepository.countAllById(eventId);
        EventState status = eventService.getEventStateStatus(eventId, event);


        /*
         * ======================================================================================
         *                     Statistic Event Detail Participant View
         * ======================================================================================
         * */

        Integer totalAttendInAnEvent = attendRepository.countAllByIdAndParticipant(eventId, participantAddress);

        GetEventDetailPStatistic statistic = new GetEventDetailPStatistic(
                totalAttendInAnEvent,
                session.size(),
                null
        );

        /*
         * ======================================================================================
         *                      Construct Response GetEventDetailP
         * ======================================================================================
         * */
        GetEventDetailP getEventDetailP = new GetEventDetailP(
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
                session,
                statistic
        );

        return ResponseEntity.ok(getEventDetailP);
    }
}
