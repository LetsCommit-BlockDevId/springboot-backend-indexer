package id.co.awan.hackathon1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDashboardPUpcomingSession {

    public BigInteger eventId;
    public String eventTitle;
    public Integer sessionNumber;
    public String sessionTitle;
    public BigInteger startSession;
    public BigInteger endSession;
    public Integer durationInHours;
    public Integer durationInMinute;

}
