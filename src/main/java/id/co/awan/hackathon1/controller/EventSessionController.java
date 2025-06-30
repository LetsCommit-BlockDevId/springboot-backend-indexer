package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.entity.Attend;
import id.co.awan.hackathon1.model.entity.Session;
import id.co.awan.hackathon1.repository.AttendRepository;
import id.co.awan.hackathon1.repository.SessionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

//@RestController
//@RequestMapping("/api/event/{eventId}/session")
@RequiredArgsConstructor
public class EventSessionController {


    private final AttendRepository attendRepository;
    private final SessionRepository sessionRepository;

    @Operation(
            summary = "Mendapatkan detail suatu session dari event"
    )
    @GetMapping
    public ResponseEntity<List<Session>> getSessions(
            @PathVariable(name = "eventId")
            @Parameter(description = "Address/Id dari Event")
            BigInteger eventId
    ) {

        List<Session> sessions = sessionRepository
                .findAllById(eventId);

        return ResponseEntity.ok(sessions);
    }

    @Operation(
            summary = "Mendapatkan detail suatu session dari event"
    )
    @GetMapping(path = "{sessionNumber}")
    public ResponseEntity<Session> getSessionDetail(
            @PathVariable(name = "eventId")
            @Parameter(description = "Address/Id dari Event")
            BigInteger eventId,

            @PathVariable(name = "sessionNumber")
            @Parameter(description = "Nomor Session")
            Integer sessionNumber
    ) {

        Session session = sessionRepository
                .findById(new Session.SessionId(eventId, sessionNumber))
                .orElse(null);

        return ResponseEntity.ok(session);
    }


    @Operation(
            summary = "Mendapatkan detail participant session di suatu event"
    )
    @GetMapping(path = "{sessionNumber}/{participantAddress}")
    public ResponseEntity<Attend> getSessionParticipantDetail(
            @PathVariable(name = "eventId")
            @Parameter(description = "Address/Id dari Event")
            BigInteger eventId,

            @PathVariable(name = "sessionNumber")
            @Parameter(description = "Nomor Session")
            Integer sessionNumber,

            @PathVariable(name = "participantAddress")
            @Parameter(description = "Participant Address")
            String participantAddress
    ) {

        Attend attend = attendRepository
                .findById(new Attend.AttendId(eventId, sessionNumber, participantAddress))
                .orElse(null);

        return ResponseEntity.ok(attend);
    }


}
