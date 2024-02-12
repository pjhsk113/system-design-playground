package study.playground.springboot.core.api.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.playground.springboot.core.api.controller.v1.request.SettlementRequest;
import study.playground.springboot.core.api.service.SettlementRequestUseCase;
import study.playground.springboot.core.api.support.common.LoginUser;

@RestController
public class SettlementRequestApi {
    private final SettlementRequestUseCase settlementRequestUseCase;

    public SettlementRequestApi(SettlementRequestUseCase settlementRequestUseCase) {
        this.settlementRequestUseCase = settlementRequestUseCase;
    }

    @PostMapping("/settlement/request")
    void settlementRequest(@LoginUser Long requestUserId, @Valid @RequestBody SettlementRequest request) {
        settlementRequestUseCase.settlementRequest(requestUserId, request.toCommand());
    }
}
