package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.*;
import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import id.co.awan.hackathon1.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // Entity
        Event event = eventRepository.findById(eventId)
                .orElse(null);

        // For field -> totalParticipant
        Integer totalParticipant = enrollRepository.countAllById(eventId);

        // For field -> getEventDetailEOSessions
        List<Session> sessions = sessionRepository.findAllById(eventId);
        List<GetEventDetailEOSession> getEventDetailEOSessions = sessions
                .stream()
                .map(eventService.sessionToEventDetailEOSession())
                .toList();

        long sessionHasActivatedLink = sessions.stream().filter(session -> session.getAttendToken() != null)
                .count();

        // For field -> status
        EventState status = eventService.getEventStateStatus(eventId, event);

        Integer totalAttendedInEvent = attendRepository.countAllById(eventId);

        // For field -> statistic
        GetEventDetailEOStatistic statistic = new GetEventDetailEOStatistic(
                // price * participant
                event.getPriceAmount().multiply(BigInteger.valueOf(totalParticipant)),
                // sesi yang memiliki link
                (int) sessionHasActivatedLink,
                // total sesi
                getEventDetailEOSessions.size(),
                // rata-rata persenan kehadiran = total semua kehadiran * 100 / total sesi
                (totalAttendedInEvent * 100) / getEventDetailEOSessions.size()
        );

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

        Event event = eventRepository.findById(eventId)
                .orElse(null);

        Integer totalParticipant = enrollRepository.countAllById(eventId);

        List<Session> sessions = sessionRepository.findAllById(eventId);
        List<GetEventDetailPSession> getEventDetailPSessions = sessions
                .stream()
                .map(eventService.sessionToEventDetailPSession(participantAddress))
                .toList();


        EventState status = eventService.getEventStateStatus(eventId, event);

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
                getEventDetailPSessions,
                null
        );

        return ResponseEntity.ok(getEventDetailP);
    }
}
