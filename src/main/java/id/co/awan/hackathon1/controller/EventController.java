package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.EventState;
import id.co.awan.hackathon1.model.dto.GetEvent;
import id.co.awan.hackathon1.model.dto.GetEventDetailEO;
import id.co.awan.hackathon1.model.dto.GetEventDetailP;
import id.co.awan.hackathon1.repository.EventRepository;
import id.co.awan.hackathon1.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    private final DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());

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

        var events = eventRepository
                .findAll()
                .stream()
                .filter(eventService.filterEventByState(eventState))
                .map(eventService.eventToGetEventDTO(eventState))
                .toList();

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

        var event = eventService.getEventById(eventId);
        var totalParticipant = eventService.getTotalParticipant(event);
        var sessions = eventService.getSessionsOrganizerView(event);
        var statistic = eventService.getEventDetailEOStatistic(event, totalParticipant, sessions.size());
        var status = eventService.getEventStatus(event);
        var canWithdraw = status.equals(EventState.FINISHED); // canWithDrawStatus only when event state finished
        var participantList = eventService.getEventParticipants(event);

        return ResponseEntity.ok(
                new GetEventDetailEO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getImageUri(),
                event.getPriceAmount(),
                event.getCommitmentAmount(),
                event.getPriceAmount().add(event.getCommitmentAmount()), // SUM Operation
                event.getStartSaleDate(),
                event.getEndSaleDate(),
                humanReadableFormatter.format(Instant.ofEpochSecond(event.getStartSaleDate().longValue())),
                humanReadableFormatter.format(Instant.ofEpochSecond(event.getEndSaleDate().longValue())),
                event.getOrganizer(),
                event.getLocation(),
                participantList,
                totalParticipant,
                event.getMaxParticipant(),
                status,
                canWithdraw,
                sessions,
                statistic
        ));
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

        participantAddress = participantAddress.toLowerCase();

        // ResponseStatusException
        eventService.validateParticipantExistInEvent(eventId, participantAddress);

        var event = eventService.getEventById(eventId);
        var sessions = eventService.getSessionsParticipantView(event, participantAddress);
        var totalParticipant = eventService.getTotalParticipant(event);
        var status = eventService.getEventStatus(event);
        var totalAttendInAnEvent = eventService.getTotalParticipantAttendInAnEvent(event, participantAddress);
        var statistic = eventService.getEventDetailPStatistic(totalAttendInAnEvent, sessions);
        var participantList = eventService.getEventParticipants(event);


        return ResponseEntity.ok(new GetEventDetailP(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getImageUri(),
                event.getPriceAmount(),
                event.getCommitmentAmount(),
                event.getPriceAmount().add(event.getCommitmentAmount()),
                null,
                null,
                humanReadableFormatter.format(Instant.ofEpochSecond(event.getStartSaleDate().longValue())),
                humanReadableFormatter.format(Instant.ofEpochSecond(event.getEndSaleDate().longValue())),
                event.getOrganizer(),
                event.getLocation(),
                participantList,
                totalParticipant,
                event.getMaxParticipant(),
                status,
                sessions,
                statistic
        ));
    }
}
