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
public class GetDashboardEOStatistic {

    private BigInteger totalRevenue;
    private BigInteger availableWithdraw;
    private BigInteger totalCommitmentFeeClaimed;
}
