package study.playground.springboot.core.api.adapter;

import study.playground.springboot.core.api.domain.User;

import java.util.List;

public interface LoadUserPort {
    User loadUser(Long userId);
    List<User> loadUsers(List<Long> userIds);
}
