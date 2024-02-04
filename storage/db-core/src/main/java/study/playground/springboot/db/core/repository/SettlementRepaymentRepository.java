package study.playground.springboot.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.playground.springboot.db.core.entity.SettlementRepaymentEntity;

import java.util.List;

public interface SettlementRepaymentRepository extends JpaRepository<SettlementRepaymentEntity, Long> {
    List<SettlementRepaymentEntity> findBySettlementId(Long settlementId);
    List<SettlementRepaymentEntity> findByTargetUserId(Long userId);
    @Query("SELECT r FROM SettlementRepaymentEntity r WHERE r.settlementId in :settlementIds and r.settlementStatus <> 'COMPLETE'")
    List<SettlementRepaymentEntity> findRemindTarget(List<Long> settlementIds);
}
