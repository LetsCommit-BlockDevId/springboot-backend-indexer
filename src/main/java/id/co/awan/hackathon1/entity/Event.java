package id.co.awan.hackathon1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "event")
public class Event {

    @Id
    private String eventAddress;
    private String title;
    private String imageUri;
    private String description;
    private String priceAmount;
    private String commitmentAmount;
    private Instant saleStart;
    private Instant saleEnd;
    private List<String> session;

}
