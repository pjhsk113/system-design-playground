package study.playground.springboot.core.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.adapter.LoadSettlementPort;
import study.playground.springboot.core.api.adapter.LoadUserPort;
import study.playground.springboot.core.api.domain.Repayments;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.core.api.service.model.SettlementReceiveHistoryCommand;
import study.playground.springboot.core.api.service.model.SettlementRequestHistoryCommand;

import java.util.List;

@Service
@Transactional
public class GetSettlementHistoryService implements GetSettlementHistoryUseCase {
    private final LoadUserPort loadUserPort;
    private final LoadSettlementPort loadSettlementPort;

    public GetSettlementHistoryService(LoadUserPort loadUserPort, LoadSettlementPort loadSettlementPort) {
        this.loadUserPort = loadUserPort;
        this.loadSettlementPort = loadSettlementPort;
    }

    @Override
    public SettlementRequestHistoryCommand getSettlementRequestHistory(Long requestUserId) {
        User user = loadUserPort.loadUser(requestUserId);
        List<Settlement> settlements = loadSettlementPort.loadSettlementRequestHistory(user.getId());

        return SettlementRequestHistoryCommand.from(settlements);
    }

    @Override
    public SettlementReceiveHistoryCommand getSettlementReceivedHistory(Long requestUserId) {
        User user = loadUserPort.loadUser(requestUserId);
        Repayments repayments = loadSettlementPort.loadSettlementReceivedHistory(user.getId());

        return SettlementReceiveHistoryCommand.from(repayments);
    }
}
