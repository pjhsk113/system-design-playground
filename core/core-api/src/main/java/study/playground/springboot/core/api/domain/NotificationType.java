package study.playground.springboot.core.api.domain;

public enum NotificationType {
    REMIND("정산 리마인드 알림");

    private String message;

    NotificationType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
