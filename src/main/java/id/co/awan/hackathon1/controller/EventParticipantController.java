package id.co.awan.hackathon1.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event/{eventAddress}/participants")
public class EventParticipantController {


    @Operation(
            summary = "Mendapatkan data participants pada event"
    )
    @GetMapping
    public ResponseEntity<String> getEventPartipants(
            @PathVariable(name = "eventAddress")
            String eventAddress
    ) {
        return ResponseEntity.ok(eventAddress);
    }

}
