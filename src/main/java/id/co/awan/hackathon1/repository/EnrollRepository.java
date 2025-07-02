package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Enroll;
import id.co.awan.hackathon1.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Enroll.EnrollId> {

    // TODO: Remove For Unused
    List<Enroll> findAllById(BigInteger id);

    /**
     * Total jumlah participant dalam sebuah event
     */
    Integer countAllById(BigInteger id);

    /**
     * Total Commitment Fee participant jika dihitung semua dari awal sampai akhir
     */
    @Query(value = "select sum(ev.priceAmount * ev.totalSession) from Enroll en join Event ev on ev.id = en.id where en.participant = :participant")
    Optional<BigInteger> totalCommitmentFee(@Param("participant") String participant);

    /**
     * Session yang akan datang, dilihat dari POV Participant
     */
    @Query(value = "select s.* from ponder.enroll e join ponder.session s on s.id = e.id where to_timestamp(s.start_session_time) > now() and e.participant = :participant", nativeQuery = true)
    List<Session> upcomingSessionByEnrolled(@Param("participant") String participant);

    /**
     * Session yang sudah berlalu, dilihat dari POV Participant
     */
    @Query(value = "select s.* from ponder.enroll e join ponder.session s on s.id = e.id where to_timestamp(s.start_session_time) < now() and e.participant = :participant", nativeQuery = true)
    List<Session> completedSessionByEnrolled(@Param("participant") String participant);

}
