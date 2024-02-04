package study.playground.springboot.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.playground.springboot.db.core.entity.SettlementEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SettlementRepository extends JpaRepository<SettlementEntity, Long> {
    List<SettlementEntity> findByRequestUserId(Long userId);

    @Query("SELECT r FROM SettlementEntity r WHERE r.remindDateTime = :currentDateTime")
    List<SettlementEntity> findByRemindDateTime(LocalDateTime currentDateTime);
}
