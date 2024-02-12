package study.playground.springboot.core.api.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import study.playground.springboot.core.api.service.model.SettlementRequestHistoryCommand;

import java.util.List;

@JsonRootName("settlementRequestHistory")
public class SettlementRequestHistoryResponse {
    private List<SettlementResponse> responses;

    private SettlementRequestHistoryResponse(List<SettlementResponse> responses) {
        this.responses = responses;
    }

    public static SettlementRequestHistoryResponse from(SettlementRequestHistoryCommand command) {
        return new SettlementRequestHistoryResponse(
                mapToResponse(command.getSettlementHistories())
        );
    }

    private static List<SettlementResponse> mapToResponse(List<SettlementRequestHistoryCommand.SettlementRequestHistory> settlementHistories) {
        return settlementHistories.stream()
                .map(SettlementResponse::from)
                .toList();
    }

    private static class SettlementResponse {
        private Long settlementId;
        private long totalSettlementAmount;
        private String settlementStatus;

        private SettlementResponse(Long settlementId, long totalSettlementAmount, String settlementStatus) {
            this.settlementId = settlementId;
            this.totalSettlementAmount = totalSettlementAmount;
            this.settlementStatus = settlementStatus;
        }

        public static SettlementResponse from(SettlementRequestHistoryCommand.SettlementRequestHistory requestHistory) {
            return new SettlementResponse(
                    requestHistory.getSettlementId().getValue(),
                    requestHistory.getTotalSettlementAmount().getAmount(),
                    requestHistory.getSettlementStatus().getMessage()
            );
        }
    }

}
