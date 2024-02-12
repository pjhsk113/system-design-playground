package study.playground.springboot.core.api.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import study.playground.springboot.core.api.service.model.SettlementReceiveHistoryCommand;

import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonRootName("settlementReceiveHistory")
public class SettlementReceiveHistoryResponse {
    private List<SettlementResponse> responses;

    private SettlementReceiveHistoryResponse(List<SettlementResponse> responses) {
        this.responses = responses;
    }

    public static SettlementReceiveHistoryResponse from(SettlementReceiveHistoryCommand command) {
        return new SettlementReceiveHistoryResponse(
                mapToResponse(command.getSettlementHistories())
        );
    }

    private static List<SettlementResponse> mapToResponse(List<SettlementReceiveHistoryCommand.SettlementReceiveHistory> receiveHistories) {
        return receiveHistories.stream()
                .map(SettlementResponse::from)
                .collect(toList());
    }

    public List<SettlementResponse> getResponses() {
        return responses;
    }

    private static class SettlementResponse {
        private Long repaymentId;
        private Long recipientId;
        private Long senderId;
        private long settlementAmount;
        private long repaymentAmount;
        private long remainAmount;
        private String settlementStatus;

        private SettlementResponse(Long repaymentId, Long recipientId, Long senderId, long settlementAmount, long repaymentAmount, long remainAmount, String settlementStatus) {
            this.repaymentId = repaymentId;
            this.recipientId = recipientId;
            this.senderId = senderId;
            this.settlementAmount = settlementAmount;
            this.repaymentAmount = repaymentAmount;
            this.remainAmount = remainAmount;
            this.settlementStatus = settlementStatus;
        }

        public static SettlementResponse from(SettlementReceiveHistoryCommand.SettlementReceiveHistory receiveHistory) {
            return new SettlementResponse(
                    receiveHistory.getRepaymentId().getValue(),
                    receiveHistory.getRecipientId().getValue(),
                    receiveHistory.getSenderId().getValue(),
                    receiveHistory.getSettlementAmount().getAmount(),
                    receiveHistory.getRepaymentAmount().getAmount(),
                    receiveHistory.getRemainAmount().getAmount(),
                    receiveHistory.getSettlementStatus().getMessage()
            );
        }

        public Long getRepaymentId() {
            return repaymentId;
        }

        public Long getRecipientId() {
            return recipientId;
        }

        public Long getSenderId() {
            return senderId;
        }

        public long getSettlementAmount() {
            return settlementAmount;
        }

        public long getRepaymentAmount() {
            return repaymentAmount;
        }

        public long getRemainAmount() {
            return remainAmount;
        }

        public String getSettlementStatus() {
            return settlementStatus;
        }
    }
}
