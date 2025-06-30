package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Attend.AttendId> {
}
