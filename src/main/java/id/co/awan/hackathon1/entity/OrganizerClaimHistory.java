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
@IdClass(OrganizerClaimHistory.OrganizerClaimHistoryId.class)
@Table(name = "organizerClaimHistory", schema = "ponder")
public class OrganizerClaimHistory {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "condition", nullable = false, length = 1)
    private Character condition;

    @Column(name = "organizer", nullable = false)
    private String organizer;

    @Column(name = "claim_amount", nullable = false, precision = 78)
    private BigInteger claimAmount;

    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrganizerClaimHistoryId implements Serializable {
        private BigInteger id;
        private Character condition;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrganizerClaimHistoryId)) return false;
            OrganizerClaimHistoryId that = (OrganizerClaimHistoryId) o;
            return Objects.equals(id, that.id) && Objects.equals(condition, that.condition);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, condition);
        }
    }

}
