package study.playground.springboot.core.api.service.model;

import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.SettlementStatus;

import java.util.List;

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
                .toList();
    }

    public List<SettlementRequestHistory> getSettlementHistories() {
        return settlementHistories;
    }

    public static class SettlementRequestHistory {
        private Settlement.SettlementId settlementId;
        private Money totalSettlementAmount;
        private SettlementStatus settlementStatus;

        private SettlementRequestHistory(Settlement.SettlementId settlementId, Money totalSettlementAmount, SettlementStatus settlementStatus) {
            this.settlementId = settlementId;
            this.totalSettlementAmount = totalSettlementAmount;
            this.settlementStatus = settlementStatus;
        }

        public static SettlementRequestHistory from(Settlement settlement) {
            return new SettlementRequestHistory(
                    settlement.getId(),
                    settlement.getTotalSettlementAmount(),
                    settlement.getSettlementStatus()
            );
        }

        public Settlement.SettlementId getSettlementId() {
            return settlementId;
        }

        public Money getTotalSettlementAmount() {
            return totalSettlementAmount;
        }

        public SettlementStatus getSettlementStatus() {
            return settlementStatus;
        }
    }

}
