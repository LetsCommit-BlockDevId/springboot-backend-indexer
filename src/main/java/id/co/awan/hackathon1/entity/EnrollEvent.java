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
@Table(name = "enrollEvent", schema = "ponder")
public class EnrollEvent {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Column(name = "participant", nullable = false)
    private String participant;

    @Column(name = "debit_amount", nullable = false, precision = 78)
    private BigInteger debitAmount;

}