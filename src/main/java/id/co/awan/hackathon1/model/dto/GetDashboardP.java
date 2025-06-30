package id.co.awan.hackathon1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDashboardP {

    public GetDashboardPStatistic statistic;
    public List<GetDashboardPUpcomingSession> upcomingSession;
    public List<GetDashboardPCompletedSession> completedSession;

}
