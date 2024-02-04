package study.playground.springboot.core.api.domain;

import lombok.Getter;

@Getter
public enum SettlementStatus {
    UNSETTLED("미정산"),
    PROCEED("정산중"),
    COMPLETE("정산 완료");

    private String message;

    SettlementStatus(String message) {
        this.message = message;
    }
}
