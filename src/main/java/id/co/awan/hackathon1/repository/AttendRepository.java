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
     * Total participant attend session in an event [A1]
     */
    @Query(value = "select count(*) from Attend where participant = :participantAddress and id = :id")
    Optional<BigInteger> countParticipantAttendInAnEvent(
            @Param("id") BigInteger eventId,
            @Param("participantAddress") String participantAddress
    );

    /**
     * Total attend in session event [A3]
     */
    Integer countAllByIdAndSession(BigInteger id, Integer session);

    /**
     * Total attendance participnat ke sebuah event
     */
    Integer countAllByIdAndParticipant(BigInteger id, String participant);


    /**
     * Total semua kehadiran yang sudah masuk di event
     */
    Integer countAllById(BigInteger id);

    /**
     * Total participant hadir di semua sesi event manapun
     */
    Integer countAllByParticipant(String participant);

    /**
     * Commitment Fee yang sudah diklaim
     */
    @Query(value = "select count(*) * ev.priceAmount from Attend aes join Event ev on ev.id = aes.id where aes.participant = :participant group by aes.id, ev.priceAmount")
    Optional<BigInteger> totalClaimedCommitmentFee(@Param("participant") String participant);

}
