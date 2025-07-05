package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Session.SessionId> {

    /**
     * Mendapatkan semua data sesi berdasarkan eventId
     */
    List<Session> findAllById(BigInteger id);

    /**
     * Mendapatkan waktu dari sesi terakir pada event
     */
    @Query(value = "SELECT s.endSessionTime FROM Session s WHERE s.id = :id ORDER BY s.session DESC limit 1")
    Optional<BigInteger> findLatestSessionTimeOfEvent(@Param("id") BigInteger id);

    /**
     * Apakah sesi terakhir event sudah selesai?
     */
    @Query(value = "SELECT CASE WHEN s.attendToken IS NOT NULL THEN TRUE ELSE FALSE END FROM Session s WHERE s.id = :id ORDER BY s.session DESC LIMIT 1")
    Optional<Boolean> isLastEventSessionHasEnd(@Param("id") BigInteger id);


    @Query(value = "select (attendToken IS NULL) from Session where id = :id and session = :session")
    Optional<Boolean> isTokenHasGenerated(@Param("id") BigInteger id, @Param("session") Integer session);

    @Query(value = "select TRUE from ponder.session where to_timestamp(start_session_time) < now() and to_timestamp(end_session_time) > now() and id = :id and session = :sessionIndex", nativeQuery = true)
    Optional<Boolean> isSessionRunning(@Param("id") BigInteger id, @Param("sessionIndex") Integer sessionIndex);

    /**
     * Berapakah session di event yang sudah dilalui?
     */
    Integer countAllByIdAndSessionBefore(BigInteger id, BigInteger time);

}
