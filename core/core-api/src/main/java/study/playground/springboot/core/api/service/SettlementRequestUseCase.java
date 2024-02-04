package study.playground.springboot.core.api.service;

import study.playground.springboot.core.api.service.model.SettlementRequestCommand;

public interface SettlementRequestUseCase {

    boolean settlementRequest(Long requestUserId, SettlementRequestCommand command);

}
