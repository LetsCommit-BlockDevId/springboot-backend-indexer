package id.co.awan.hackathon1.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class SessionId {
    private String eventAddress;
    private Integer number;
}
