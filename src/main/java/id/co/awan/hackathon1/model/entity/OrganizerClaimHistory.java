package id.co.awan.hackathon1.model.entity;

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
@Table(name = "organizer_claim_history", schema = "ponder")
public class OrganizerClaimHistory {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "session", nullable = false, precision = 78)
    private BigInteger sessionIndex;

    @Column(name = "condition", nullable = false, length = 1)
    private Character condition;

//    @EmbeddedId
//    OrganizerClaimHistoryId id;

    @Column(name = "organizer", nullable = false)
    private String organizer;

    @Column(name = "claim_amount", nullable = false, precision = 78)
    private BigInteger claimAmount;

    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
//    @Embeddable
    public static class OrganizerClaimHistoryId implements Serializable {

//        @Column(name = "id", nullable = false, precision = 78)
        private BigInteger id;

//        @Column(name = "condition", nullable = false, length = 1)
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
