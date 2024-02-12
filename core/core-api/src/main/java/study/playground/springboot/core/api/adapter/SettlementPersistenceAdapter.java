package study.playground.springboot.core.api.adapter;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.domain.Repayment;
import study.playground.springboot.core.api.domain.Repayments;
import study.playground.springboot.core.api.domain.Settlement;
import study.playground.springboot.core.api.domain.User;
import study.playground.springboot.db.core.entity.SettlementEntity;
import study.playground.springboot.db.core.entity.SettlementRepaymentEntity;
import study.playground.springboot.db.core.repository.SettlementRepaymentRepository;
import study.playground.springboot.db.core.repository.SettlementRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SettlementPersistenceAdapter implements SettlementStatePort, LoadSettlementPort {
    private final SettlementMapper settlementMapper;
    private final SettlementRepository settlementRepository;
    private final SettlementRepaymentRepository settlementRepaymentRepository;

    public SettlementPersistenceAdapter(SettlementMapper settlementMapper, SettlementRepository settlementRepository, SettlementRepaymentRepository settlementRepaymentRepository) {
        this.settlementMapper = settlementMapper;
        this.settlementRepository = settlementRepository;
        this.settlementRepaymentRepository = settlementRepaymentRepository;
    }

    @Override
    public void createSettlement(Settlement settlement) {
        SettlementEntity settlementEntity = settlementRepository.save(settlementMapper.mapToEntity(settlement));
        settlementRepaymentRepository.saveAll(settlementMapper.mapToRepaymentEntities(settlementEntity.getId(), settlement.getRepayments()));
    }

    @Override
    public void updateSettlementState(Settlement settlement, Repayment repayment) {
        SettlementEntity settlementEntity = settlementRepository.save(settlementMapper.mapToEntity(settlement));
        settlementRepaymentRepository.save(settlementMapper.mapToRepaymentEntity(settlementEntity.getId(), repayment));
    }

    @Override
    @Transactional(readOnly = true)
    public Settlement loadSettlement(Settlement.SettlementId settlementId) {
        SettlementEntity settlementEntity =
                settlementRepository.findById(settlementId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        List<SettlementRepaymentEntity> settlementRepaymentEntity =
                settlementRepaymentRepository.findBySettlementId(settlementEntity.getId());

        return settlementMapper.mapToDomain(settlementEntity, settlementRepaymentEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Settlement> loadSettlementRequestHistory(User.UserId userId) {
        List<SettlementEntity> settlementEntities = settlementRepository.findByRequestUserId(userId.getValue());
        List<Settlement> settlements = new ArrayList<>();

        for (SettlementEntity settlementEntity : settlementEntities) {
            List<SettlementRepaymentEntity> settlementRepaymentEntity =
                    settlementRepaymentRepository.findBySettlementId(settlementEntity.getId());
            settlements.add(settlementMapper.mapToDomain(settlementEntity, settlementRepaymentEntity));
        }

        return settlements;
    }

    @Override
    @Transactional(readOnly = true)
    public Repayments loadSettlementReceivedHistory(User.UserId userId) {
        List<SettlementRepaymentEntity> settlementRepaymentEntity =
                settlementRepaymentRepository.findByTargetUserId(userId.getValue());
        return settlementMapper.mapToRepayments(settlementRepaymentEntity);
    }
}
