package study.playground.springboot.core.api.adapter;

import study.playground.springboot.core.api.domain.User;

public interface UserStatePort {
    void update(User user);
}
