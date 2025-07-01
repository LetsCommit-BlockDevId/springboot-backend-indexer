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
     * */
    @Query(value = "select count(*) from Attend where participant = :participantAddress and id = :id")
    Optional<BigInteger> countParticipantAttendInAnEvent(
            @Param("id") BigInteger eventId,
            @Param("participantAddress") String participantAddress
    );

    /**
     * Total attend in session event [A3]
     * */
    Integer countAllByIdAndSession(BigInteger id, Integer session);

    /**
     * Total semua kehadiran yang sudah masuk di event
     * */
    Integer countAllById(BigInteger id);

}
