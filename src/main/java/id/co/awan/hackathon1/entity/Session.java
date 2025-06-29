package id.co.awan.hackathon1.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "session")
public class Session {

    @EmbeddedId
    SessionId sessionId;

    private Integer number;
    private Instant sessionStart;
    private Instant sessionEnd;
    private List<String> attendances;

}
