package id.co.awan.hackathon1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDetailEO {

    public BigInteger eventId;
    public String title;
    public String description;
    public String imageUri;

    public BigInteger priceAmount;
    public BigInteger commitmentAmount;
    public BigInteger totalAmount;

    public BigInteger startSaleDate;
    public BigInteger endSaleDate;

    public String organizer;
    public String location;

    public Integer participant;
    public Integer maxParticipant;

    public EventState status;

    public Boolean canWithdraw;

    public List<GetEventDetailEOSession> session;
    public GetEventDetailEOStatistic statistic;

}
