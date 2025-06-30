package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.EventState;
import id.co.awan.hackathon1.model.dto.GetEventResponse;
import id.co.awan.hackathon1.mapper.EventMapper;
import id.co.awan.hackathon1.model.dto.GetEventResponse;
import id.co.awan.hackathon1.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private EventRepository eventRepository;

    @Operation(
            summary = "Mendapatkan detail dari event"
//            description = "Ini Deskripsi"
//            responses = {
//                    @ApiResponse(
//                            description = "Successful Operation",
//                            responseCode = "200",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = GetEventResponse.class)
//                            )
//                    )
//            }
    )
    @GetMapping(path = "{eventAddress}")
    public ResponseEntity<GetEventResponse> getEventDetail(
            @PathVariable(name = "eventAddress")
            @Parameter(description = "Berisi Event Address")
            String eventAddress
    ) {
        return ResponseEntity.ok(null);
    }


    @Operation(
            summary = "Mendapatkan data event berdasarkan creator"
    )
    @GetMapping(path = "{creatorAddress}")
    public ResponseEntity<GetEventResponse> getEventByCreator(
            @PathVariable(name = "creatorAddress")
            String creatorAddress
    ) {
        return ResponseEntity.ok(null);
    }


    @Operation(
            summary = "Mendapatkan data event berdasarkan state"
    )
    @GetMapping
    public ResponseEntity<List<GetEventResponse>> getEvent(
            @RequestParam(name = "state")
            EventState eventState
    ) {

        List<GetEventResponse> events = eventRepository.findAll()
                .stream()
                .filter(event -> {

                    if (eventState.equals(EventState.FINISHED)) {

                        return true;
                    } else if (eventState.equals(EventState.ON_GOING)) {
                        // On Going
                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
                                .isBefore(Instant.now());

                    } else {
                        // On Sale
                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
                                .isAfter(Instant.now());
                    }
                })
                .map(EventMapper.INSTANCE::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }
}
