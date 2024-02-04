package study.playground.springboot.core.api.adapter;

import study.playground.springboot.core.api.domain.Repayment;
import study.playground.springboot.core.api.domain.Settlement;

public interface SettlementStatePort {
    void createSettlement(Settlement settlement);
    void updateSettlementState(Settlement settlement, Repayment repayment);
}
