package study.playground.springboot.core.api.domain;

import java.time.LocalDateTime;

public class Settlement {

    private SettlementId id;

    private final Repayments repayments;

    private final Money totalSettlementAmount;

    private final LocalDateTime remindDatetime;

    private Settlement(SettlementId id, Repayments repayments, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        this.id = id;
        this.repayments = repayments;
        this.totalSettlementAmount = totalSettlementAmount;
        this.remindDatetime = remindDatetime;
    }

    public static Settlement withoutId(Repayments repayments, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        return new Settlement(null, repayments, totalSettlementAmount, remindDatetime);
    }

    public static Settlement withId(SettlementId settlementId, Repayments repayments, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        return new Settlement(settlementId, repayments, totalSettlementAmount, remindDatetime);
    }

    public SettlementId getId() {
        return id;
    }

    public Repayments getRepayments() {
        return repayments;
    }

    public Money getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public LocalDateTime getRemindDatetime() {
        return remindDatetime;
    }

    public SettlementStatus getSettlementStatus() {
        if (repayments.hasRemainSettlement()) {
            return SettlementStatus.PROCEED;
        }
        return SettlementStatus.COMPLETE;
    }

    public Money getSystemPaymentAmount() {
        int participantsCount = repayments.size();
        if (totalSettlementAmount.hasRemainValue(participantsCount)) {
            return totalSettlementAmount.remainder(participantsCount);
        }

        return Money.ZERO;
    }

    public Repayment getSourceUserRepayment(Long userId) {
        return repayments.getSourceUserRepayment(userId);
    }

    public Repayment getOnwerUserRepayment() {
        return repayments.getOwnerUserRepayment();
    }

    public Settlement updateSettlement(Repayment repayment) {
        return Settlement.withId(
                this.id,
                this.repayments.updateRepayments(repayment),
                this.totalSettlementAmount,
                this.remindDatetime
        );
    }

    public static class SettlementId {
        private final Long value;

        public SettlementId(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }
}
