package id.co.awan.hackathon1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event/{eventAddress}/session")
public class EventSessionController {

    @Operation(
            summary = "Mendapatkan detail suatu session dari event"
    )
    @GetMapping()
    public ResponseEntity<List<String>> getSessions(
            @PathVariable(name = "eventAddress")
            @Parameter(description = "Address/Id dari Event")
            String eventAddress
    ) {
        return ResponseEntity.ok(List.of(
                eventAddress
        ));
    }

    @Operation(
            summary = "Mendapatkan detail suatu session dari event"
    )
    @GetMapping(path = "{sessionNumber}")
    public ResponseEntity<List<String>> getSessionDetail(
            @PathVariable(name = "eventAddress")
            @Parameter(description = "Address/Id dari Event")
            String eventAddress,

            @PathVariable(name = "sessionNumber")
            @Parameter(description = "Nomor Session")
            Integer sessionNumber
    ) {
        return ResponseEntity.ok(List.of(
                eventAddress,
                sessionNumber.toString()
        ));
    }


    @Operation(
            summary = "Mendapatkan detail participant session di suatu event"
    )
    @GetMapping(path = "{sessionNumber}/{participantAddress}")
    public ResponseEntity<List<String>> getSessionParticipantDetail(
            @PathVariable(name = "eventAddress")
            @Parameter(description = "Address/Id dari Event")
            String eventAddress,

            @PathVariable(name = "sessionNumber")
            @Parameter(description = "Nomor Session")
            Integer sessionNumber,

            @PathVariable(name = "participantAddress")
            @Parameter(description = "Participant Address")
            String participantAddress
    ) {
        return ResponseEntity.ok(List.of(
                eventAddress,
                sessionNumber.toString(),
                participantAddress
        ));
    }


}
