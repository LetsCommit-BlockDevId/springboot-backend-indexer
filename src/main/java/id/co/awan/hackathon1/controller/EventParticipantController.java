package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.entity.Enroll;
import id.co.awan.hackathon1.repository.EnrollRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/event/{eventId}/participants")
@RequiredArgsConstructor
public class EventParticipantController {

    private EnrollRepository enrollRepository;

    @Operation(
            summary = "Mendapatkan data participants pada event"
    )
    @GetMapping
    public ResponseEntity<List<String>> getEventPartipants(
            @PathVariable(name = "eventId")
            BigInteger eventId
    ) {

        List<String> participants = enrollRepository.findAllById(eventId)
                .stream()
                .filter(enroll -> enroll.getId().equals(eventId))
                .map(Enroll::getParticipant)
                .toList();

        return ResponseEntity.ok(participants);
    }

}
