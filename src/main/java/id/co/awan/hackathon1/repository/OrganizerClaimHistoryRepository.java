package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.entity.OrganizerClaimHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrganizerClaimHistoryRepository extends JpaRepository<OrganizerClaimHistory, OrganizerClaimHistory.OrganizerClaimHistoryId> {

    /**
     * Jumlah revenue dari event fee yang sudah diklaim oleh organizer
     */
    @Query(value = "select sum(och.claimAmount) from OrganizerClaimHistory och where och.organizer = :organizer")
    BigInteger totalClaimedRevenue(@Param("organizer") String organizer);


}
