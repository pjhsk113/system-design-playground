package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import study.playground.springboot.db.core.BaseTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column
    private Long targetUserId;

    @Column
    private Long settlementId;

    @Column
    private Long settlementRepaymentId;

    @Column
    private long totalSettlementAmount;

    @Column
    private long settlementAmount;

    @Column
    private String notificationType;

    @Column
    private LocalDateTime remindDateTime;

    protected NotificationEntity() { }

    public NotificationEntity(Long id, Long targetUserId, Long settlementId, Long settlementRepaymentId, long totalSettlementAmount, long settlementAmount, String notificationType, LocalDateTime remindDateTime) {
        this.id = id;
        this.targetUserId = targetUserId;
        this.settlementId = settlementId;
        this.settlementRepaymentId = settlementRepaymentId;
        this.totalSettlementAmount = totalSettlementAmount;
        this.settlementAmount = settlementAmount;
        this.notificationType = notificationType;
        this.remindDateTime = remindDateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public Long getSettlementRepaymentId() {
        return settlementRepaymentId;
    }

    public long getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public long getSettlementAmount() {
        return settlementAmount;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public LocalDateTime getRemindDateTime() {
        return remindDateTime;
    }
}
