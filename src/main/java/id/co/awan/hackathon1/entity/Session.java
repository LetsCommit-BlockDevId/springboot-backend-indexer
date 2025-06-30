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
@IdClass(Session.SessionId.class)
@Table(name = "session", schema = "ponder")
public class Session {

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "session", nullable = false)
    private Integer session;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "start_session_time", nullable = false)
    private BigInteger startSessionTime;

    @Column(name = "end_session_time", nullable = false)
    private String endSessionTime;

    @Column(name = "attend_token", nullable = true)
    private String attendToken;

    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SessionId implements Serializable {

        private BigInteger id;
        private Integer session;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SessionId that)) return false;
            return Objects.equals(id, that.id) && Objects.equals(session, that.session);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, session);
        }
    }
}
