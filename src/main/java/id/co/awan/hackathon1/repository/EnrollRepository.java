package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Enroll.EnrollId> {

    List<Enroll> findAllById(BigInteger id);

    // Participant on Event - /participant [A2]
    Integer countAllById(BigInteger id);


}
