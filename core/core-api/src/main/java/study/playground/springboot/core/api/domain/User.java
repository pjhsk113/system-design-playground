package study.playground.springboot.core.api.domain;

public class User {

    private final UserId id;

    private final Money balance;

    private User(UserId id, Money balance) {
        this.id = id;
        this.balance = balance;
    }

    public static User withId(UserId userId, Money balance) {
        return new User(userId, balance);
    }

    public UserId getId() {
        return id;
    }

    public Money getBalance() {
        return balance;
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

    public static class UserId {
        private Long value;

        public UserId(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }
}
