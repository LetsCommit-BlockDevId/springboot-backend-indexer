package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EventRepository extends JpaRepository<Event, BigInteger> {
}
