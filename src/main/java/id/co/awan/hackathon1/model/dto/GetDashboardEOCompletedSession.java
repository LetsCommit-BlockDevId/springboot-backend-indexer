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
public class GetDashboardEOCompletedSession {

    private BigInteger eventId;
    private String eventTitle;

    private Integer sessionNumber;
    private String sessionTitle;

    private BigInteger startSession;
    private BigInteger endSession;

    private String startSessionHumanReadable;
    private String endSessionHumanReadable;

    private Integer attendance;
    private Integer totalParticipants;

    private Boolean isLinkGenerated;

}
