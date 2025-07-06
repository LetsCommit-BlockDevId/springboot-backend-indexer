package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Attend.AttendId> {

    /**
     * Total kehadiran semua participant pada satu sesi di satu event
     */
    Integer countAllByIdAndSession(BigInteger id, Integer session);

    /**
     * Total kehadiran si participant pada satu event
     */
    Integer countAllByIdAndParticipant(BigInteger id, String participant);


    /**
     * Total semua kehadiran yang sudah masuk di semua session, dari satu event
     */
    Integer countAllById(BigInteger id);

    /**
     * Total hadir si participant di semua session, dari semua event
     */
    Integer countAllByParticipant(String participant);

    /**
     * Commitment Fee yang sudah diklaim oleh participant dari semua event yang ada
     */
    @Query(value = "select count(*) * ev.commitmentAmount from Attend aes join Event ev on ev.id = aes.id where aes.participant = :participant group by aes.id, ev.priceAmount")
    Optional<BigInteger> totalClaimedCommitmentFee(@Param("participant") String participant);

}
