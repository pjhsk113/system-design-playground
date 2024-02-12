package study.playground.springboot.core.api.service.model;

import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Settlement;

public class SendMoneyCommand {
    private Settlement.SettlementId settlementId;
    private Money amount;

    private SendMoneyCommand(Settlement.SettlementId settlementId, Money amount) {
        this.settlementId = settlementId;
        this.amount = amount;
    }

    public static SendMoneyCommand of(Long settlementId, long amount) {
        return new SendMoneyCommand(
                new Settlement.SettlementId(settlementId),
                Money.of(amount));
    }

    public Settlement.SettlementId getSettlementId() {
        return settlementId;
    }

    public Money getAmount() {
        return amount;
    }
}
