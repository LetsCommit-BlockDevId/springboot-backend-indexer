package id.co.awan.hackathon1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private BigInteger commitmentAmount;
    private BigInteger totalAmount;

    private String priceAmountUsdFormat;
    private String commitmentAmountUsdFormat;
    private String totalAmountUsdFormat;

    private BigInteger startSaleDate;
    private BigInteger endSaleDate;

    private String startSaleDateHumanReadable;
    private String endSaleDateHumanReadable;

    private String organizer;
    private String location;

    private Integer participant;
    private Integer maxParticipant;

    private EventState status;
}
