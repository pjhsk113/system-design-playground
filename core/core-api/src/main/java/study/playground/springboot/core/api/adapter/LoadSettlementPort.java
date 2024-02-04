package study.playground.springboot.core.api.adapter;

import study.playground.springboot.core.api.domain.Repayments;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.User;

import java.util.List;

public interface LoadSettlementPort {
    Settlement loadSettlement(Settlement.SettlementId settlementId);
    List<Settlement> loadSettlementRequestHistory(User.UserId userId);
    Repayments loadSettlementReceivedHistory(User.UserId userId);
}
