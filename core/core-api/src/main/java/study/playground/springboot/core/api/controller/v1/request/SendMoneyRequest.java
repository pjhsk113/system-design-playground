package study.playground.springboot.core.api.controller.v1.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.playground.springboot.core.api.service.model.SendMoneyCommand;

@Getter
@NoArgsConstructor
public class SendMoneyRequest {

    @NotNull
    private Long settlementId;

    @Positive
    @Max(value = 5_000_000)
    private long amount;

    public SendMoneyCommand toCommand() {
        return SendMoneyCommand.of(
                settlementId,
                amount
        );
    }
}
