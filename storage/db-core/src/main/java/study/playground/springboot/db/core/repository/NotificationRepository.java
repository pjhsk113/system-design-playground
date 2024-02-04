package study.playground.springboot.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.playground.springboot.db.core.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
