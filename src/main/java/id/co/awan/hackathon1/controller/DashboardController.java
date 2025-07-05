package id.co.awan.hackathon1.controller;

import id.co.awan.hackathon1.model.dto.GetDashboardEO;
import id.co.awan.hackathon1.model.dto.GetDashboardP;
import id.co.awan.hackathon1.service.DashboardService;
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

    private final DashboardService dashboardService;

    @Operation(
            summary = "Mendapatkan data dashboard sebagai participant"
    )
    @GetMapping(path = "/participant")
    public ResponseEntity<GetDashboardP>
    getDashboardViewParticipant(
            @PathVariable(name = "address")
            String participantAddress
    ) {

        participantAddress = participantAddress.toLowerCase();

        var statistic = dashboardService.getParticipantStatistic(participantAddress);
        var upcomingSessions = dashboardService.getParticipantUpcomingSession(participantAddress);
        var completedSession = dashboardService.getParticipantCompletedSession(participantAddress);

        return ResponseEntity.ok(new GetDashboardP(
                statistic,
                upcomingSessions,
                completedSession
        ));
    }


    // DONE
    @Operation(
            summary = "Mendapatkan data dashboard sebagai event organizer"
    )
    @GetMapping(path = "/organizer")
    public ResponseEntity<GetDashboardEO>
    getDashboardViewEventOrganizer(
            @PathVariable(name = "address")
            String organizerAddress
    ) {

        organizerAddress = organizerAddress.toLowerCase();

        var statistic = dashboardService.getOrganizerStatistic(organizerAddress);
        var upcomingSession = dashboardService.getOrganizerUpcomingSession(organizerAddress);
        var completedSession = dashboardService.getOrganizerCompletedSession(organizerAddress);

        return ResponseEntity.ok(new GetDashboardEO(
                statistic,
                upcomingSession,
                completedSession
        ));
    }


}
