package study.playground.springboot.core.api.domain;

public class Repayment {

    private RepaymentId id;

    private final User.UserId ownerUserId;

    private final User.UserId targetUserId;

    private final Money settlementAmount;

    private final Money repaymentAmount;

    public Repayment(User.UserId ownerUserId,
                     User.UserId targetUserId,
                     Money settlementAmount,
                     Money repaymentAmount) {
        this.id = null;
        this.ownerUserId = ownerUserId;
        this.targetUserId = targetUserId;
        this.settlementAmount = settlementAmount;
        this.repaymentAmount = repaymentAmount;
    }

    public Repayment(RepaymentId id, User.UserId ownerUserId, User.UserId targetUserId, Money settlementAmount, Money repaymentAmount) {
        this.id = id;
        this.ownerUserId = ownerUserId;
        this.targetUserId = targetUserId;
        this.settlementAmount = settlementAmount;
        this.repaymentAmount = repaymentAmount;
    }

    public RepaymentId getId() {
        return id;
    }

    public User.UserId getOwnerUserId() {
        return ownerUserId;
    }

    public User.UserId getTargetUserId() {
        return targetUserId;
    }

    public Money getSettlementAmount() {
        return settlementAmount;
    }

    public Money getRepaymentAmount() {
        return repaymentAmount;
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

    public static class RepaymentId {
        private Long value;

        public RepaymentId(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }
}
