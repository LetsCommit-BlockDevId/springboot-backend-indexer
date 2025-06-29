package id.co.awan.hackathon1.entity;

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
@Table(name = "organizerClaimUnattendedHistory", schema = "ponder")
public class organizerClaimUnattendedHistory {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Column(name = "session", nullable = false)
    private Integer session;

    @Column(name = "unattended_person", nullable = false)
    private Integer unattendedPerson;

    @Column(name = "organizer", nullable = false)
    private String organizer;

    @Column(name = "claim_amount", nullable = false, precision = 78)
    private BigInteger claimAmount;
}
