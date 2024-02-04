package study.playground.springboot.core.api.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.playground.springboot.core.api.domain.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class SettlementReceiveHistoryCommand {
    private List<SettlementReceiveHistory> settlementHistories;

    private SettlementReceiveHistoryCommand(List<SettlementReceiveHistory> settlementHistories) {
        this.settlementHistories = settlementHistories;
    }

    public static SettlementReceiveHistoryCommand from(Repayments repayments) {
        return new SettlementReceiveHistoryCommand(mapToCommand(repayments.getRepayments()));
    }

    private static List<SettlementReceiveHistory> mapToCommand(List<Repayment> repayments) {
        return repayments.stream()
                .map(SettlementReceiveHistory::from)
                .collect(toList());
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SettlementReceiveHistory {
        private Repayment.RepaymentId repaymentId;
        private User.UserId recipientId;
        private User.UserId senderId;
        private Money settlementAmount;
        private Money repaymentAmount;
        private Money remainAmount;
        private SettlementStatus settlementStatus;

        public static SettlementReceiveHistory from(Repayment repayment) {
            return new SettlementReceiveHistory(
                    repayment.getId(),
                    repayment.getOwnerUserId(),
                    repayment.getTargetUserId(),
                    repayment.getSettlementAmount(),
                    repayment.getRepaymentAmount(),
                    repayment.getRemainAmount(),
                    repayment.getSettlementStatus()
            );
        }
    }
}
