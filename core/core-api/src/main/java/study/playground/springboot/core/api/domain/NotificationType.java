package study.playground.springboot.core.api.domain;

import lombok.Getter;

@Getter
public enum NotificationType {
    REMIND("정산 리마인드 알림");

    private String message;

    NotificationType(String message) {
        this.message = message;
    }
}
