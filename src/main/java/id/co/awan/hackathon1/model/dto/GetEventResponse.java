package id.co.awan.hackathon1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetEventResponse {

    private BigDecimal id;
    private String title;
    private String description;
    private String imageUri;
    private BigDecimal priceAmount;
    private BigDecimal commitmentAmount;
    private Long totalSession;
    private BigDecimal startSaleDate;
    private BigDecimal endSaleDate;
    private String organizer;

}
