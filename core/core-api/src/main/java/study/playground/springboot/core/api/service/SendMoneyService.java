package study.playground.springboot.core.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.adapter.LoadSettlementPort;
import study.playground.springboot.core.api.adapter.LoadUserPort;
import study.playground.springboot.core.api.adapter.SettlementStatePort;
import study.playground.springboot.core.api.adapter.UserStatePort;
import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Repayment;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.core.api.service.model.SendMoneyCommand;
import study.playground.springboot.core.api.support.exception.ExceedBalanceException;
import study.playground.springboot.core.api.support.exception.SettlementProcessingException;

@Service
@Transactional
public class SendMoneyService implements SendMoneyUseCase {
    private final LoadUserPort loadUserPort;
    private final UserStatePort userStatePort;
    private final LoadSettlementPort loadSettlementPort;
    private final SettlementStatePort settlementStatePort;

    public SendMoneyService(LoadUserPort loadUserPort, UserStatePort userStatePort, LoadSettlementPort loadSettlementPort, SettlementStatePort settlementStatePort) {
        this.loadUserPort = loadUserPort;
        this.userStatePort = userStatePort;
        this.loadSettlementPort = loadSettlementPort;
        this.settlementStatePort = settlementStatePort;
    }

    @Override
    public boolean sendMoney(Long requestUserId, SendMoneyCommand command) {
        Settlement settlement = loadSettlementPort.loadSettlement(command.getSettlementId());
        Repayment repayment = settlement.getSourceUserRepayment(requestUserId);
        Money amount = command.getAmount();

        User sourceUser = loadUserPort.loadUser(requestUserId);
        User targetUser = loadUserPort.loadUser(repayment.getOwnerUserId().getValue());

        validateSettlementStatus(repayment);
        validateSendMoneyAvailable(sourceUser, amount);

        repayment = repayment.paid(amount);
        settlement = settlement.updateSettlement(repayment);

        userStatePort.update(sourceUser.withdraw(amount));
        userStatePort.update(targetUser.deposit(amount));
        settlementStatePort.updateSettlementState(settlement, repayment);

        return true;
    }

    private void validateSettlementStatus(Repayment repayment) {
        if (repayment.isComplete()) {
            throw new SettlementProcessingException("이미 완료된 정산 내역입니다.");
        }
    }

    private void validateSendMoneyAvailable(User sourceUser, Money amount) {
        if (!sourceUser.isEnoughBalance(amount)) {
            throw new ExceedBalanceException("송금 요청 금액이 잔고보다 큽니다. 잔고를 확인해주세요.");
        }
    }
}
