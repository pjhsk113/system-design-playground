package study.playground.springboot.core.api.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class NotificationEvent {
    @Getter
    private NotificationEventId id;

    @Getter
    private final LocalDateTime remindDatetime;

    public NotificationEvent(@NonNull LocalDateTime remindDatetime) {
        this.id = null;
        this.remindDatetime = remindDatetime;
    }

    @Value
    public static class NotificationEventId {
        private Long value;
    }
}
