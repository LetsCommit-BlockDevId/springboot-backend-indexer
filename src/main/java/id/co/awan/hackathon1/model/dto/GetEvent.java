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
public class GetEvent {
    private BigInteger eventId;
    private String title;
    private String description;
    private String imageUri;

    private BigInteger priceAmount;
    private Integer commitmentAmount;
    private BigInteger totalAmount;

    private BigInteger startSaleDate;
    private BigInteger endSaleDate;

    private String organizer;
    private String location;

    private Integer participant;
    private Integer maxParticipant;

    private EventState status;
}
