package id.co.awan.hackathon1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.awan.hackathon1.model.dto.GetDashboardEO;
import id.co.awan.hackathon1.model.dto.GetDashboardP;
import id.co.awan.hackathon1.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/{address}")
@RequiredArgsConstructor
public class DashboardController {

//    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    // DONE
    @Operation(
            summary = "Mendapatkan data dashboard sebagai participant"
    )
    @GetMapping(path = "/participant")
    public ResponseEntity<GetDashboardP> getDashboardViewParticipant(
            @PathVariable(name = "address")
            String participantAddress
    ) {
        try {
            GetDashboardP getDashboardP = objectMapper.readValue("""
                    {
                      "statistic": {
                        "totalDeposit": 0,
                        "totalCommitmentFeeAvailable": 0,
                        "totalCommitmentFeeClaimed": 0
                      },
                      "upcomingSession": [
                        {
                          "eventId": 0,
                          "eventTitle": "DeFi Fundamentals",
                          "sessionNumber": 0,
                          "sessionTitle": "Introduction to DeFi",
                          "startSession": 1751295986,
                          "endSession": 1751295986,
                          "durationInHours": 1,
                          "durationInMinute": 60
                        }
                      ],
                      "completedSession": [
                        {
                          "eventId": 0,
                          "eventTitle": "DeFi Fundamentals",
                          "sessionNumber": 0,
                          "sessionTitle": "Introduction to DeFi",
                          "startSession": 1751295986,
                          "endSession": 1751295986,
                          "isAttended": true
                        }
                      ]
                    }
                    """, new TypeReference<GetDashboardP>() {
            });

            return ResponseEntity.ok(getDashboardP);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // DONE
    @Operation(
            summary = "Mendapatkan data dashboard sebagai event organizer"
    )
    @GetMapping(path = "/organizer")
    public ResponseEntity<GetDashboardEO> getDashboardViewEventOrganizer(
            @PathVariable(name = "address")
            String organizerAddress
    ) {
        try {
            GetDashboardEO getDashboardEO = objectMapper.readValue("""
                    {
                      "statistic": {
                        "totalRevenue": 0,
                        "availableWithdraw": 0,
                        "totalCommitmentFeeClaimed": 0
                      },
                      "upcomingSession": [
                        {
                          "eventId": 0,
                          "eventTitle": "DeFi Fundamentals",
                          "sessionNumber": 0,
                          "sessionTitle": "Introduction to DeFi",
                          "startSession": 1751295986,
                          "endSession": 1751295986,
                          "durationInHours": 1,
                          "durationInMinute": 60
                        }
                      ],
                      "completedSession": [
                        {
                          "eventId": 0,
                          "eventTitle": "DeFi Fundamentals",
                          "sessionNumber": 0,
                          "sessionTitle": "Introduction to DeFi",
                          "startSession": 1751295986,
                          "endSession": 1751295986,
                          "isLinkGenerated": true
                        }
                      ]
                    }
                    """, new TypeReference<GetDashboardEO>() {
            });

            return ResponseEntity.ok(getDashboardEO);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
