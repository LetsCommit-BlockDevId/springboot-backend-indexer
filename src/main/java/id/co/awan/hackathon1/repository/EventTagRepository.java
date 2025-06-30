package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.entity.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTagRepository extends JpaRepository<EventTag, EventTag.EventTagId> {
}
