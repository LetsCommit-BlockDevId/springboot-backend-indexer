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
public class GetEventDetailEOSession {

    private Integer sessionNumber;
    private String title;

    private BigInteger startSession;
    private BigInteger endSession;

    private Integer durationInHours;
    private Integer durationInMinute;

    private BigInteger peopleAttend;

    private SessionStatus status;
    private boolean activeQrButton;

}
