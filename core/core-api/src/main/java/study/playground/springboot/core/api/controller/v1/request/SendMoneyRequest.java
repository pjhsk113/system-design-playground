package study.playground.springboot.core.api.controller.v1.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import study.playground.springboot.core.api.service.model.SendMoneyCommand;

public class SendMoneyRequest {

    @NotNull
    private Long settlementId;

    @Positive
    @Max(value = 5_000_000)
    private long amount;

    public SendMoneyRequest() { }

    public SendMoneyCommand toCommand() {
        return SendMoneyCommand.of(
                settlementId,
                amount
        );
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public long getAmount() {
        return amount;
    }
}
