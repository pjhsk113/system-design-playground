package study.playground.springboot.core.api.controller.v1.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import study.playground.springboot.core.api.service.model.SettlementRequestCommand;

import java.util.List;

public class SettlementRequest {

    @Size(max = 50, min = 2)
    private List<Long> targetUsers;

    @Positive
    @Max(value = 5_000_000)
    private long totalSettlementAmount;

    @Pattern(regexp = "^(?:[0-9]{4}-[0-9]{2}-[0-9]{2} (?:[01]?[0-9]|2[0-3]):(?:00|30))$",
            message = "날짜는 30분 단위로 입력 가능합니다.")
    private String remindDateTime;

    public SettlementRequest() { }

    public SettlementRequestCommand toCommand() {
        return SettlementRequestCommand.of(
                targetUsers,
                totalSettlementAmount,
                remindDateTime
        );
    }

    public List<Long> getTargetUsers() {
        return targetUsers;
    }

    public long getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public String getRemindDateTime() {
        return remindDateTime;
    }
}
