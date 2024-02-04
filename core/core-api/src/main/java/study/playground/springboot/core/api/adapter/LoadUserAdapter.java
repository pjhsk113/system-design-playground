package study.playground.springboot.core.api.adapter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.db.core.entity.UserEntity;
import study.playground.springboot.db.core.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadUserAdapter implements LoadUserPort {
    private final UserRepository userRepository;

    @Override
    public User loadUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        return User.withId(
                new User.UserId(userEntity.getId()),
                Money.of(userEntity.getBalance()));
    }

    @Override
    public List<User> loadUsers(List<Long> userIds) {
        List<UserEntity> userEntities = userRepository.findAllByIdIn(userIds);

        return userEntities.stream()
                .map(userEntity -> User.withId(
                        new User.UserId(userEntity.getId()),
                        Money.of(userEntity.getBalance())))
                .collect(toList());
    }
}
