package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.playground.springboot.db.core.BaseTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "settlement")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
