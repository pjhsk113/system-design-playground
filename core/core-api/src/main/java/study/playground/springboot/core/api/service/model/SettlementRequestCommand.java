package study.playground.springboot.core.api.service.model;

import study.playground.springboot.core.api.domain.Money;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SettlementRequestCommand {
    private List<Long> targetUserIds;
    private Money totalSettlementAmount;
    private LocalDateTime remindDatetime;

    private SettlementRequestCommand(List<Long> targetUserIds, Money totalSettlementAmount, LocalDateTime remindDatetime) {
        this.targetUserIds = targetUserIds;
        this.totalSettlementAmount = totalSettlementAmount;
        this.remindDatetime = remindDatetime;
    }

    public static SettlementRequestCommand of(List<Long> targetUsers, long totalSettlementAmount, String remindDatetime) {
        return new SettlementRequestCommand(
                targetUsers,
                Money.of(totalSettlementAmount),
                LocalDateTime.parse(remindDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public List<Long> getTargetUserIds() {
        return targetUserIds;
    }

    public Money getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public LocalDateTime getRemindDatetime() {
        return remindDatetime;
    }
}
