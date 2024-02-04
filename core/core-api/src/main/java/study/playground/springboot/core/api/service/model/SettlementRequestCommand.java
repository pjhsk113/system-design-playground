package study.playground.springboot.core.api.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.playground.springboot.core.api.domain.Money;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SettlementRequestCommand {
    private List<Long> targetUserIds;
    private Money totalSettlementAmount;
    private LocalDateTime remindDatetime;

    public static SettlementRequestCommand of(List<Long> targetUsers, long totalSettlementAmount, String remindDatetime) {
        return new SettlementRequestCommand(
                targetUsers,
                Money.of(totalSettlementAmount),
                LocalDateTime.parse(remindDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
