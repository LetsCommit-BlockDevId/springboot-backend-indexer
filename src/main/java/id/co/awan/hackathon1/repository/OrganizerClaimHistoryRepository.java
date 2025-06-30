package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.entity.EventTag;
import id.co.awan.hackathon1.entity.OrganizerClaimHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerClaimHistoryRepository extends JpaRepository<OrganizerClaimHistory, OrganizerClaimHistory.OrganizerClaimHistoryId> {
}
