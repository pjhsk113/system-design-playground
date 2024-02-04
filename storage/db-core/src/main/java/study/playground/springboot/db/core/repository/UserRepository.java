package study.playground.springboot.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.playground.springboot.db.core.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByIdIn(List<Long> userIds);
}
