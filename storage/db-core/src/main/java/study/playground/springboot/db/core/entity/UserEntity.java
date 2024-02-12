package study.playground.springboot.db.core.entity;

import jakarta.persistence.*;
import study.playground.springboot.db.core.BaseTime;

@Entity
@Table(name = "user")
public class UserEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private long balance;

    protected UserEntity() { }

    public UserEntity(Long id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }
}
