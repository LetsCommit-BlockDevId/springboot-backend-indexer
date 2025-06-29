package id.co.awan.hackathon1.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {

    @Operation(
            summary = "Mendapatkan counter dari event yang dibuat"
    )
    @GetMapping(path = "/events")
    public ResponseEntity<Integer> getStatisticEvent(
    ) {
        return ResponseEntity.ok(10);
    }

    @Operation(
            summary = "Mendapatkan counter dari participant yang telah join"
    )
    @GetMapping(path = "/participants")
    public ResponseEntity<Integer> getStatisticParticipant(
    ) {
        return ResponseEntity.ok(10);
    }

    @Operation(
            summary = "Mendapatkan balance yang sudah dimanage oleh platform"
    )
    @GetMapping(path = "/managed-balance")
    public ResponseEntity<BigInteger> getStatisticManagedBalance(
    ) {
        return ResponseEntity.ok(BigInteger.ONE);
    }


}
