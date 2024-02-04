package study.playground.springboot.core.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Settlement {

    @Getter
    private SettlementId id;

    @Getter
    private final Repayments repayments;

    @Getter
    private final Money totalSettlementAmount;

    @Getter
    private final LocalDateTime remindDatetime;


    public static Settlement withoutId(Repayments repayments, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        return new Settlement(null, repayments, totalSettlementAmount, remindDatetime);
    }

    public static Settlement withId(SettlementId settlementId, Repayments repayments, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        return new Settlement(settlementId, repayments, totalSettlementAmount, remindDatetime);
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

    @Value
    public static class SettlementId {
        private final Long value;
    }
}
