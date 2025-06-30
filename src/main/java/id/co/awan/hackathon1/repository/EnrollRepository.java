package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Enroll.EnrollId> {
}
