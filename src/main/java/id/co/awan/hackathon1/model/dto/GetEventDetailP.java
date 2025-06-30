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
public class GetEventDetailP {

    private BigInteger eventId;
    private String title;
    private String description;
    private String imageUri;

    private BigInteger priceAmount;
    private BigInteger commitmentAmount;
    private BigInteger totalAmount; //total dari dua diatas

    private BigInteger startSaleDate;
    private BigInteger endSaleDate;

    private String organizer;
    private String location;

    private Integer participant;
    private Integer maxParticipant;

    private EventState status;
    private List<GetEventDetailPSession> session;
    private GetEventDetailPStatistic statistic;

}
