package study.playground.springboot.core.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.playground.springboot.core.api.controller.v1.response.SettlementReceiveHistoryResponse;
import study.playground.springboot.core.api.controller.v1.response.SettlementRequestHistoryResponse;
import study.playground.springboot.core.api.service.GetSettlementHistoryUseCase;
import study.playground.springboot.core.api.support.common.LoginUser;

@RestController
public class GetSettlementHistoryApi {
    private final GetSettlementHistoryUseCase getSettlementHistoryUseCase;

    public GetSettlementHistoryApi(GetSettlementHistoryUseCase getSettlementHistoryUseCase) {
        this.getSettlementHistoryUseCase = getSettlementHistoryUseCase;
    }

    @GetMapping("/settlement/request/history")
    ResponseEntity<SettlementRequestHistoryResponse> readRequestHistory(@LoginUser Long requestUserId) {
        return ResponseEntity.ok(
                SettlementRequestHistoryResponse.from(getSettlementHistoryUseCase.getSettlementRequestHistory(requestUserId))
        );
    }

    @GetMapping("/settlement/receive/history")
    ResponseEntity<SettlementReceiveHistoryResponse> readReceiveHistory(@LoginUser Long requestUserId) {
        return ResponseEntity.ok(
                SettlementReceiveHistoryResponse.from(getSettlementHistoryUseCase.getSettlementReceivedHistory(requestUserId))
        );

    }
}
