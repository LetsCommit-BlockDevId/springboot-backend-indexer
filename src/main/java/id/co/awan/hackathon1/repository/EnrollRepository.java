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

    List<Enroll> findAllById(BigInteger id);

    // Participant on Event - /participant [A2]
    Integer countAllById(BigInteger id);

    /**
     * Total Commitment Fee participant jika dihitung semua berdasarkan log
     */
    @Query(value = "select sum(ev.priceAmount * ev.totalSession) from Enroll en join Event ev on ev.id = en.id where en.participant = :participant")
    Optional<BigInteger> totalCommitmentFee(@Param("participant") String participant);


    @Query(value = "select s.* from ponder.enroll e join ponder.session s on s.id = e.id where to_timestamp(s.start_session_time) > now() and e.participant = :participant",nativeQuery = true)
    List<Session> upcomingSessionByEnrolled(@Param("participant") String participant);

    @Query(value = "select s.* from ponder.enroll e join ponder.session s on s.id = e.id where to_timestamp(s.start_session_time) < now() and e.participant = :participant",nativeQuery = true)
    List<Session> completedSessionByEnrolled(@Param("participant") String participant);

}
