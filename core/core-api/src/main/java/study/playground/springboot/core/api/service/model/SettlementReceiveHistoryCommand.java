package study.playground.springboot.core.api.service.model;

import study.playground.springboot.core.api.domain.*;

import java.util.List;

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
                .toList();
    }

    public List<SettlementReceiveHistory> getSettlementHistories() {
        return settlementHistories;
    }

    public static class SettlementReceiveHistory {
        private Repayment.RepaymentId repaymentId;
        private User.UserId recipientId;
        private User.UserId senderId;
        private Money settlementAmount;
        private Money repaymentAmount;
        private Money remainAmount;
        private SettlementStatus settlementStatus;

        private SettlementReceiveHistory(Repayment.RepaymentId repaymentId, User.UserId recipientId, User.UserId senderId, Money settlementAmount, Money repaymentAmount, Money remainAmount, SettlementStatus settlementStatus) {
            this.repaymentId = repaymentId;
            this.recipientId = recipientId;
            this.senderId = senderId;
            this.settlementAmount = settlementAmount;
            this.repaymentAmount = repaymentAmount;
            this.remainAmount = remainAmount;
            this.settlementStatus = settlementStatus;
        }

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

        public Repayment.RepaymentId getRepaymentId() {
            return repaymentId;
        }

        public User.UserId getRecipientId() {
            return recipientId;
        }

        public User.UserId getSenderId() {
            return senderId;
        }

        public Money getSettlementAmount() {
            return settlementAmount;
        }

        public Money getRepaymentAmount() {
            return repaymentAmount;
        }

        public Money getRemainAmount() {
            return remainAmount;
        }

        public SettlementStatus getSettlementStatus() {
            return settlementStatus;
        }
    }
}
