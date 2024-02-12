package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import study.playground.springboot.db.core.BaseTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "settlement")
public class SettlementEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_id")
    private Long id;

    @Column
    private Long requestUserId;

    @Column
    private long totalSettlementAmount;

    @Column
    private long systemPaymentAmount;

    @Column
    private String settlementStatus;

    @Column
    private LocalDateTime remindDateTime;

    protected SettlementEntity() { }

    public SettlementEntity(Long id, Long requestUserId, long totalSettlementAmount, long systemPaymentAmount, String settlementStatus, LocalDateTime remindDateTime) {
        this.id = id;
        this.requestUserId = requestUserId;
        this.totalSettlementAmount = totalSettlementAmount;
        this.systemPaymentAmount = systemPaymentAmount;
        this.settlementStatus = settlementStatus;
        this.remindDateTime = remindDateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getRequestUserId() {
        return requestUserId;
    }

    public long getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    public long getSystemPaymentAmount() {
        return systemPaymentAmount;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public LocalDateTime getRemindDateTime() {
        return remindDateTime;
    }
}
