package study.playground.springboot.core.api.domain;

import lombok.Value;

@Value
public class Money {
    public static final Money ZERO = Money.of(0L);

    private final long amount;

    public static Money of(long value) {
        return new Money(value);
    }

    public static Money add(Money a, Money b) {
        return new Money(a.amount + b.amount);
    }

    public static Money subtract(Money a, Money b) {
        return new Money(a.amount - b.amount);
    }

    public Money minus(Money money){
        return new Money(this.amount - money.amount);
    }

    public Money plus(Money money){
        return new Money(this.amount + money.amount);
    }

    public Money divide(int count){
        return new Money(this.amount / count);
    }

    public Money remainder(int count){
        return new Money(this.amount % count);
    }

    public boolean isNegative() {
        return this.amount < 0L;
    }

    public boolean hasRemainValue(int count){
        return this.amount % count > 0L;
    }

    public boolean isZero(){
        return this.amount == 0L;
    }

    public boolean isGreaterThanOrEquals(Money money){
        return this.amount >= money.amount;
    }

    public boolean isGreaterThan(Money money){
        return this.amount > money.amount;
    }
}
