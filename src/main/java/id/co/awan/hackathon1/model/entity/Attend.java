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
@IdClass(Attend.AttendId.class)
@Table(name = "attend_event_session", schema = "ponder")
public class Attend {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "session", nullable = false)
    private Integer session;

    @Id
    @Column(name = "participant", nullable = false)
    private String participant;

    @Column(name = "attend_token", nullable = true)
    private String attendToken;

    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AttendId implements Serializable {
        private BigInteger id;
        private Integer session;
        private String participant;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AttendId that)) return false;
            return Objects.equals(id, that.id) &&
                    Objects.equals(session, that.session) &&
                    Objects.equals(participant, that.participant);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, session, participant);
        }
    }
}
