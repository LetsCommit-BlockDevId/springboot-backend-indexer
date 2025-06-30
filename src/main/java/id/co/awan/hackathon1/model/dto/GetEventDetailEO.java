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

    public int eventId;
    public String title;
    public String description;
    public String imageUri;

    public int priceAmount;
    public int commitmentAmount;
    public int totalAmount;

    public int startSaleDate;
    public int endSaleDate;

    public String organizer;
    public String location;

    public int participant;
    public int maxParticipant;

    public String status;

    public boolean canWithdraw;

    public List<GetEventDetailEOSession> session;
    public GetEventDetailEOStatistic statistic;

}
