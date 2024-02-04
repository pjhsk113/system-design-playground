package study.playground.springboot.core.api.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Repayment {

    @Getter
    private RepaymentId id;

    @Getter
    private final User.UserId ownerUserId;

    @Getter
    private final User.UserId targetUserId;

    @Getter
    private final Money settlementAmount;

    @Getter
    private final Money repaymentAmount;

    public Repayment(
            @NonNull User.UserId ownerUserId,
            @NonNull User.UserId targetUserId,
            @NonNull Money settlementAmount,
            @NonNull Money repaymentAmount) {
        this.id = null;
        this.ownerUserId = ownerUserId;
        this.targetUserId = targetUserId;
        this.settlementAmount = settlementAmount;
        this.repaymentAmount = repaymentAmount;
    }

    public boolean isSettlementOwner() {
        return this.ownerUserId.equals(targetUserId);
    }

    public boolean isSettlementTargetUser(User.UserId userId) {
        return this.targetUserId.equals(userId);
    }

    public boolean isComplete() {
        return getSettlementStatus() == SettlementStatus.COMPLETE;
    }

    public SettlementStatus getSettlementStatus() {
        if (repaymentAmount.isGreaterThanOrEquals(settlementAmount)) {
            return SettlementStatus.COMPLETE;
        }

        if (repaymentAmount.isZero()) {
            return SettlementStatus.UNSETTLED;
        }

        return SettlementStatus.PROCEED;
    }

    public Money getRemainAmount() {
        Money remainAmount = Money.subtract(settlementAmount, repaymentAmount);
        return remainAmount.isNegative() ? Money.ZERO : remainAmount;
    }

    public Repayment paid(Money amount) {
        return new Repayment(
                this.id,
                this.ownerUserId,
                this.targetUserId,
                this.settlementAmount,
                repaymentAmount.plus(amount)
        );
    }

    @Value
    public static class RepaymentId {
        private Long value;
    }
}
