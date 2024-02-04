package study.playground.springboot.core.api.service;

import study.playground.springboot.core.api.service.model.SendMoneyCommand;

public interface SendMoneyUseCase {

    boolean sendMoney(Long requestUserId, SendMoneyCommand command);

}
