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
public class GetPlatformStatistic {

    private BigInteger totalEventCreated;
    private BigInteger totalParticipantEnrolled;
    private BigInteger totalBalanceManaged;

}
