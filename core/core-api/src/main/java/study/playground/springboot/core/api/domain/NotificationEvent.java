package study.playground.springboot.core.api.domain;

import java.time.LocalDateTime;

public class NotificationEvent {
    private NotificationEventId id;

    private final LocalDateTime remindDatetime;

    public NotificationEvent(LocalDateTime remindDatetime) {
        this.id = null;
        this.remindDatetime = remindDatetime;
    }

    public NotificationEventId getId() {
        return id;
    }

    public LocalDateTime getRemindDatetime() {
        return remindDatetime;
    }

    public static class NotificationEventId {
        private Long value;

        public NotificationEventId(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }
}
