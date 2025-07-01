package id.co.awan.hackathon1.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event", schema = "ponder")
public class Event {
    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Column(name = "price_amount", nullable = false, precision = 78)
    private BigInteger priceAmount;

    @Column(name = "commitment_amount", nullable = false, precision = 78)
    private BigInteger commitmentAmount;

    @Column(name = "total_session", nullable = false)
    private Integer totalSession;

    @Column(name = "max_participant", nullable = false)
    private Integer maxParticipant;

    @Column(name = "start_sale_date", nullable = false, precision = 78)
    private BigInteger startSaleDate;

    @Column(name = "end_sale_date", nullable = false, precision = 78)
    private BigInteger endSaleDate;

    @Column(name = "organizer", nullable = false)
    private String organizer;
}
