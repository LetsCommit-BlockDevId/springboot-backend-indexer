package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository
        extends JpaRepository<Session, Session.SessionId> {
}
