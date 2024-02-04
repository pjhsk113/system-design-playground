package study.playground.springboot.core.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.adapter.LoadUserPort;
import study.playground.springboot.core.api.adapter.SettlementStatePort;
import study.playground.springboot.core.api.domain.Money;
import study.playground.springboot.core.api.domain.Repayments;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.core.api.service.model.SettlementRequestCommand;
import study.playground.springboot.core.api.support.exception.UserStateException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SettlementRequestService implements SettlementRequestUseCase {
    private final LoadUserPort loadUserPort;
    private final SettlementStatePort settlementStatePort;

    @Override
    public boolean settlementRequest(Long requestUserId, SettlementRequestCommand command) {
        List<Long> targetUserIds = command.getTargetUserIds();
        Money totalSettlementAmount = command.getTotalSettlementAmount();
        LocalDateTime remindDatetime = command.getRemindDatetime();

        List<User> users = loadUserPort.loadUsers(targetUserIds);
        validateUsers(targetUserIds, users);

        Repayments repayments = Repayments.of(requestUserId, totalSettlementAmount, targetUserIds);
        Settlement settlement = Settlement.withoutId(repayments, totalSettlementAmount, remindDatetime);

        settlementStatePort.createSettlement(settlement);
        return true;
    }

    private void validateUsers(List<Long> userIds, List<User> users) {
        if (userIds.size() != users.size()) {
            throw new UserStateException("올바르지 않은 유저 정보가 존재합니다.");
        }
    }
}
