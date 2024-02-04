package study.playground.springboot.core.api.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Settlement;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SendMoneyCommand {
    private Settlement.SettlementId settlementId;
    private Money amount;

    public static SendMoneyCommand of(Long settlementId, long amount) {
        return new SendMoneyCommand(
                new Settlement.SettlementId(settlementId),
                Money.of(amount));
    }
}
