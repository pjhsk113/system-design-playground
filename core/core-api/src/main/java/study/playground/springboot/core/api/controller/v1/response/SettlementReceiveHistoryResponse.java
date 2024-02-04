package study.playground.springboot.core.api.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.playground.springboot.core.api.service.model.SettlementReceiveHistoryCommand;

import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonRootName("settlementReceiveHistory")
@Getter
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

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class SettlementResponse {
        private Long repaymentId;
        private Long recipientId;
        private Long senderId;
        private long settlementAmount;
        private long repaymentAmount;
        private long remainAmount;
        private String settlementStatus;

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
    }
}
