package id.co.awan.hackathon1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enroll", schema = "ponder")
@IdClass(Enroll.EnrollId.class)
public class Enroll {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "participant", nullable = false)
    private String participant;

    @Column(name = "debit_amount", nullable = false, precision = 78)
    private BigInteger debitAmount;


    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnrollId implements Serializable {

        private BigInteger id;
        private String participant;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Enroll.EnrollId that)) return false;
            return Objects.equals(id, that.id) && Objects.equals(participant, that.participant);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, participant);
        }
    }

}