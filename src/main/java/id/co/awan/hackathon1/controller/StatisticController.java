package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.GetPlatformStatistic;
import id.co.awan.hackathon1.repository.EnrollRepository;
import id.co.awan.hackathon1.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final EventRepository eventRepository;
    private final EnrollRepository enrollRepository;

//    @Operation(
//            summary = "Mendapatkan counter dari event yang dibuat"
//    )
//    @GetMapping(path = "/events")
//    public ResponseEntity<Long> getStatisticEvent(
//    ) {
//
//        return ResponseEntity.ok(eventRepository.count());
//    }
//
//    @Operation(
//            summary = "Mendapatkan counter dari participant yang telah join"
//    )
//    @GetMapping(path = "/participants")
//    public ResponseEntity<Long> getStatisticParticipant(
//    ) {
//
//        return ResponseEntity.ok(enrollRepository.count());
//    }
//
//    @Operation(
//            summary = "Mendapatkan balance yang sudah dimanage oleh platform"
//    )
//    @GetMapping(path = "/managed-balance")
//    public ResponseEntity<BigInteger> getStatisticManagedBalance(
//    ) {
//
//        BigInteger managedBalance = enrollRepository.findAll()
//                .stream()
//                .map(Enroll::getDebitAmount)
//                .reduce(BigInteger.ZERO, BigInteger::add);
//
//        return ResponseEntity.ok(managedBalance);
//    }

    @Operation(
            summary = "Mendapatkan balance yang sudah dimanage oleh platform"
    )
    @GetMapping(path = "/platform")
    public ResponseEntity<GetPlatformStatistic> getPlatformStatistic(
    ) {

        long totalEventCreated = eventRepository.count();
        long totalParticipantEnroll = enrollRepository.count();
        BigInteger totalBalanceManaged = enrollRepository.getTotalBalanceManaged();

        return ResponseEntity.ok(new GetPlatformStatistic(
                BigInteger.valueOf(totalEventCreated),
                BigInteger.valueOf(totalParticipantEnroll),
                totalBalanceManaged
        ));
    }


}
