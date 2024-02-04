package study.playground.springboot.core.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.playground.springboot.core.api.controller.v1.request.SendMoneyRequest;
import study.playground.springboot.core.api.service.SendMoneyUseCase;
import study.playground.springboot.core.api.support.common.LoginUser;

@RestController
@RequiredArgsConstructor
public class SendMoneyApi {
    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/settlement/send")
    void sendMoney(@LoginUser Long requestUserId, @Valid @RequestBody SendMoneyRequest request) {
        sendMoneyUseCase.sendMoney(requestUserId, request.toCommand());
    }
}
