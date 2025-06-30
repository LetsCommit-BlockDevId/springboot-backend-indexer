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
@IdClass(EventTag.EventTagId.class)
@Table(name = "event_tag", schema = "ponder")
public class EventTag {

//    @EmbeddedId
//    EventTagId id;

    @Id
    @Column(name = "id", nullable = false, precision = 78)
    private BigInteger id;

    @Id
    @Column(name = "index", nullable = false)
    private Integer index;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    // Composite Key Class
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
//    @Embeddable
    public static class EventTagId implements Serializable {

//        @Column(name = "id", nullable = false, precision = 78)
        private BigInteger id;

//        @Column(name = "index", nullable = false)
        private Integer index;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EventTagId that)) return false;
            return Objects.equals(id, that.id) && Objects.equals(index, that.index);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, index);
        }
    }
}
