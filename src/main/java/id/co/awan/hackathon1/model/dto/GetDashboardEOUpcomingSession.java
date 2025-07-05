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
public class GetDashboardEOUpcomingSession {

    public BigInteger eventId;
    public String eventTitle;
    private Integer totalParticipants;

    public Integer sessionNumber;
    public String sessionTitle;

    public BigInteger startSession;
    public BigInteger endSession;

    public String startSessionHumanReadable;
    public String endSessionHumanReadable;

    public Integer durationInHours;
    public Integer durationInMinute;

    public Boolean generateLinkButtonEnable;

}
