package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Event;
import id.co.awan.hackathon1.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, BigInteger> {

    @Query(value = "select s.* from ponder.event ev join ponder.session s on s.id = ev.id where to_timestamp(s.start_session_time) > now() and ev.organizer = :organizer",nativeQuery = true)
    List<Session> upcomingSessionByOrganizer(@Param("organizer") String organizer);

    @Query(value = "select s.* from ponder.event ev join ponder.session s on s.id = ev.id where to_timestamp(s.start_session_time) < now() and ev.organizer = :organizer",nativeQuery = true)
    List<Session> completedSessionByOrganizer(@Param("organizer") String organizer);

}
