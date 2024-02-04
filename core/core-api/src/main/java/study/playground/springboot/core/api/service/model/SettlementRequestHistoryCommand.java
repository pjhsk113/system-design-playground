package study.playground.springboot.core.api.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.SettlementStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class SettlementRequestHistoryCommand {
    private List<SettlementRequestHistory> settlementHistories;

    private SettlementRequestHistoryCommand(List<SettlementRequestHistory> settlementRequestHistories) {
        this.settlementHistories = settlementRequestHistories;
    }

    public static SettlementRequestHistoryCommand from(List<Settlement> settlements) {
        return new SettlementRequestHistoryCommand(mapToCommand(settlements));
    }

    private static List<SettlementRequestHistory> mapToCommand(List<Settlement> settlements) {
        return settlements.stream()
                .map(SettlementRequestHistory::from)
                .collect(toList());
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SettlementRequestHistory {
        private Settlement.SettlementId settlementId;
        private Money totalSettlementAmount;
        private SettlementStatus settlementStatus;

        public static SettlementRequestHistory from(Settlement settlement) {
            return new SettlementRequestHistory(
                    settlement.getId(),
                    settlement.getTotalSettlementAmount(),
                    settlement.getSettlementStatus()
            );
        }
    }

}
