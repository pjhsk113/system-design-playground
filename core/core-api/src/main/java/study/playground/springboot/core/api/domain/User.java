package study.playground.springboot.core.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Getter
    private final UserId id;

    @Getter
    private final Money balance;

    public static User withId(UserId userId, Money balance) {
        return new User(userId, balance);
    }

    public boolean isEnoughBalance(Money amount) {
        return balance.isGreaterThanOrEquals(amount);
    }

    public User deposit(Money amount) {
        return User.withId(this.id, balance.plus(amount));
    }

    public User withdraw(Money amount) {
        if (!isPositive(amount)) {
            return User.withId(this.id, Money.ZERO);
        }
        return User.withId(this.id, balance.minus(amount));
    }

    private boolean isPositive(Money amount) {
        return balance.minus(amount).getAmount() >= 0L;
    }

    @Value
    public static class UserId {
        private Long value;
    }
}
