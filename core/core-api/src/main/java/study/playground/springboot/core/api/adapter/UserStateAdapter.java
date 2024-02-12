package study.playground.springboot.core.api.adapter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.db.core.entity.UserEntity;
import study.playground.springboot.db.core.repository.UserRepository;

@Component
@Transactional
public class UserStateAdapter implements UserStatePort {
    private final UserRepository userRepository;

    public UserStateAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void update(User user) {
        UserEntity mappedUserEntity =
                new UserEntity(
                        user.getId().getValue(),
                        user.getBalance().getAmount());

        userRepository.save(mappedUserEntity);
    }

}
