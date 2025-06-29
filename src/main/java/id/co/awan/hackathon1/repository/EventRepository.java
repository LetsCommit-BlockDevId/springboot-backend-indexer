package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EventRepository extends JpaRepository<Event, BigDecimal> {
}
