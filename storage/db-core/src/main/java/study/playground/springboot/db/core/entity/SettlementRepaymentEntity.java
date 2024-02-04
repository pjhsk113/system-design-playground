package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.playground.springboot.db.core.BaseTime;

@Entity
@Table(name = "settlement_repayment")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

}
