package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.OrganizerClaimHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface organizerClaimUnattendedHistoryRepository
        extends JpaRepository<OrganizerClaimHistory, OrganizerClaimHistory.OrganizerClaimHistoryId> {
}
