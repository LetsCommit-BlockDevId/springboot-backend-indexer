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
public class GetDashboardEO {

    public GetDashboardEOStatistic statistic;
    public List<GetDashboardEOUpcomingSession> upcomingSession;
    public List<GetDashboardEOCompletedSession> completedSession;

}
