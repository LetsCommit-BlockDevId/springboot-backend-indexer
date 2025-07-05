package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, BigInteger> {

    /**
     * Session yang akan datang, dilihat dari POV Organizer
     */
    @Query(value = "select s.* from ponder.event ev join ponder.session s on s.id = ev.id where to_timestamp(s.start_session_time) > now() and ev.organizer = :organizer", nativeQuery = true)
    List<Session> upcomingSessionByOrganizer(@Param("organizer") String organizer);

    /**
     * Session yang sudah berlalu, dilihat dari POV Organizer
     */
    @Query(value = "select s.* from ponder.event ev join ponder.session s on s.id = ev.id where to_timestamp(s.start_session_time) < now() and ev.organizer = :organizer", nativeQuery = true)
    List<Session> completedSessionByOrganizer(@Param("organizer") String organizer);

    /**
     * Penghasilan total dari organizer dalam membuat event = participant * event fee
     */
    @Query(value = "select sum(en.debitAmount) from Event ev join Enroll en on en.id = ev.id where ev.organizer = :organizer")
    Optional<BigInteger> totalRevenueOfOrganizer(@Param("organizer") String organizer);

}
