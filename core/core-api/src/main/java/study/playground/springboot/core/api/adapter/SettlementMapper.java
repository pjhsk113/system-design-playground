package study.playground.springboot.core.api.adapter;

import org.springframework.stereotype.Component;
import study.playground.springboot.core.api.domain.*;
import study.playground.springboot.db.core.entity.SettlementEntity;
import study.playground.springboot.db.core.entity.SettlementRepaymentEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettlementMapper {

    Settlement mapToDomain(SettlementEntity settlement, List<SettlementRepaymentEntity> repayments) {
        return Settlement.withId(
                new Settlement.SettlementId(settlement.getId()),
                mapToRepayments(repayments),
                Money.of(settlement.getTotalSettlementAmount()),
                settlement.getRemindDateTime());
    }

    Repayments mapToRepayments(List<SettlementRepaymentEntity> repayments) {
        List<Repayment> mappedRepayments = new ArrayList<>();

        for (SettlementRepaymentEntity repayment : repayments) {
            mappedRepayments.add(
                    new Repayment(
                            new Repayment.RepaymentId(repayment.getId()),
                            new User.UserId(repayment.getOwnerUserId()),
                            new User.UserId(repayment.getTargetUserId()),
                            Money.of(repayment.getSettlementAmount()),
                            Money.of(repayment.getRepaymentAmount())
                    ));
        }

        return Repayments.from(mappedRepayments);
    }

    SettlementEntity mapToEntity(Settlement settlement) {
        return new SettlementEntity(
                settlement.getId() == null ? null : settlement.getId().getValue(),
                settlement.getOnwerUserRepayment().getOwnerUserId().getValue(),
                settlement.getTotalSettlementAmount().getAmount(),
                settlement.getSystemPaymentAmount().getAmount(),
                settlement.getSettlementStatus().name(),
                settlement.getRemindDatetime()
        );
    }

    List<SettlementRepaymentEntity> mapToRepaymentEntities(Long settlementId, Repayments repayments) {
        List<SettlementRepaymentEntity> mappedRepaymentEntity = new ArrayList<>();

        for (Repayment repayment : repayments.getRepayments()) {
            mappedRepaymentEntity.add(
                    new SettlementRepaymentEntity(
                            repayment.getId() == null ? null : repayment.getId().getValue(),
                            settlementId,
                            repayment.getOwnerUserId().getValue(),
                            repayment.getTargetUserId().getValue(),
                            repayment.getSettlementAmount().getAmount(),
                            repayment.getRepaymentAmount().getAmount(),
                            repayment.getSettlementStatus().name()
                    )
            );
        }

        return mappedRepaymentEntity;
    }

    SettlementRepaymentEntity mapToRepaymentEntity(Long settlementId, Repayment repayment) {
        return new SettlementRepaymentEntity(
                repayment.getId() == null ? null : repayment.getId().getValue(),
                settlementId,
                repayment.getOwnerUserId().getValue(),
                repayment.getTargetUserId().getValue(),
                repayment.getSettlementAmount().getAmount(),
                repayment.getRepaymentAmount().getAmount(),
                repayment.getSettlementStatus().name()
        );
    }
}
