package study.playground.springboot.core.api.adapter;

import com.settlementsystem.infrastructure.notification.SendNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.domain.NotificationEvent;
import study.playground.springboot.core.api.domain.NotificationType;
import study.playground.springboot.db.core.entity.NotificationEntity;
import study.playground.springboot.db.core.entity.SettlementEntity;
import study.playground.springboot.db.core.entity.SettlementRepaymentEntity;
import study.playground.springboot.db.core.repository.NotificationRepository;
import study.playground.springboot.db.core.repository.SettlementRepaymentRepository;
import study.playground.springboot.db.core.repository.SettlementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@Async("asyncExecutor")
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class NotificationEventHandler {
    private final NotificationRepository notificationRepository;
    private final SettlementRepository settlementRepository;
    private final SettlementRepaymentRepository settlementRepaymentRepository;
    private final SendNotification sendNotification;

    @EventListener
    public void handleNotificationEvent(NotificationEvent notificationEvent) {
        List<SettlementEntity> settlementEntities =
                settlementRepository.findByRemindDateTime(notificationEvent.getRemindDatetime());

        if (settlementEntities.isEmpty()) {
            return;
        }

        List<SettlementRepaymentEntity> settlementRepaymentEntities =
                settlementRepaymentRepository.findRemindTarget(mapToIds(settlementEntities));

        // 알림 발송 mock api
        List<String> contents = convertToContents(settlementRepaymentEntities);
        if (!contents.isEmpty()) {
            sendNotification.send(contents);
        }

        List<NotificationEntity> mappedNotificationEntity = new ArrayList<>();

        for (SettlementEntity settlement : settlementEntities) {
            for (SettlementRepaymentEntity repayment : settlementRepaymentEntities) {

                if (isSameId(settlement.getId(), repayment.getSettlementId())) {
                    mappedNotificationEntity.add(mapToEntity(settlement, repayment));
                }

            }
        }

        notificationRepository.saveAll(mappedNotificationEntity);
    }

    private NotificationEntity mapToEntity(SettlementEntity settlement, SettlementRepaymentEntity repayment) {
        return new NotificationEntity(
                null,
                repayment.getTargetUserId(),
                repayment.getSettlementId(),
                repayment.getId(),
                settlement.getTotalSettlementAmount(),
                repayment.getSettlementAmount(),
                NotificationType.REMIND.name(),
                settlement.getRemindDateTime()
        );
    }

    private List<Long> mapToIds(List<SettlementEntity> settlementEntities) {
        return settlementEntities.stream()
                .map(SettlementEntity::getId)
                .collect(toList());
    }

    private List<String> convertToContents(List<SettlementRepaymentEntity> settlementRepaymentEntities) {
        return settlementRepaymentEntities.stream()
                .map(entity -> NotificationType.REMIND.getMessage())
                .collect(Collectors.toList());

    }

    private boolean isSameId(Long sourceId, Long targetId) {
        return sourceId.equals(targetId);
    }
}
