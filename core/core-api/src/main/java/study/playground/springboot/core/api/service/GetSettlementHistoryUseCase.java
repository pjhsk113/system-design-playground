package study.playground.springboot.core.api.service;

import study.playground.springboot.core.api.service.model.SettlementReceiveHistoryCommand;
import study.playground.springboot.core.api.service.model.SettlementRequestHistoryCommand;

public interface GetSettlementHistoryUseCase {
    SettlementRequestHistoryCommand getSettlementRequestHistory(Long requestUserId);
    SettlementReceiveHistoryCommand getSettlementReceivedHistory(Long requestUserId);
}
