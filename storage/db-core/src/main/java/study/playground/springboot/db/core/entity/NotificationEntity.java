package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.playground.springboot.db.core.BaseTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
