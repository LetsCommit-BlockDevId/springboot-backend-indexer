package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.dto.EventState;
import id.co.awan.hackathon1.model.EventModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Operation(
            summary = "Mendapatkan detail dari event"
//            description = "Ini Deskripsi"
//            responses = {
//                    @ApiResponse(
//                            description = "Successful Operation",
//                            responseCode = "200",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = EventModel.class)
//                            )
//                    )
//            }
    )
    @GetMapping(path = "{eventAddress}")
    public ResponseEntity<EventModel> getEventDetail(
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
    public ResponseEntity<EventModel> getEventByCreator(
            @PathVariable(name = "creatorAddress")
            String creatorAddress
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "Mendapatkan data event berdasarkan state"
    )
    @GetMapping
    public ResponseEntity<String> getEvent(
            @RequestParam(name = "state")
            EventState eventState
    ) {
        return ResponseEntity.ok(eventState.getValue());
    }

}
