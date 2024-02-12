package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import study.playground.springboot.db.core.BaseTime;

@Entity
@Table(name = "settlement_repayment")
public class SettlementRepaymentEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_repayment_id")
    private Long id;

    @Column
    private Long settlementId;

    @Column
    private Long ownerUserId;

    @Column
    private Long targetUserId;

    @Column
    private long settlementAmount;

    @Column
    private long repaymentAmount;

    @Column
    private String settlementStatus;

    protected SettlementRepaymentEntity() { }

    public SettlementRepaymentEntity(Long id, Long settlementId, Long ownerUserId, Long targetUserId, long settlementAmount, long repaymentAmount, String settlementStatus) {
        this.id = id;
        this.settlementId = settlementId;
        this.ownerUserId = ownerUserId;
        this.targetUserId = targetUserId;
        this.settlementAmount = settlementAmount;
        this.repaymentAmount = repaymentAmount;
        this.settlementStatus = settlementStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public long getSettlementAmount() {
        return settlementAmount;
    }

    public long getRepaymentAmount() {
        return repaymentAmount;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }
}
