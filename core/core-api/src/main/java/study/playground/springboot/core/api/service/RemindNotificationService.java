package study.playground.springboot.core.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.playground.springboot.core.api.domain.NotificationEvent;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class RemindNotificationService {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(cron = "0 0/30 * * * *")
    public void sendRemind() {
        applicationEventPublisher.publishEvent(
                new NotificationEvent(
                        LocalDateTime.now().withSecond(0).withNano(0)
                )
        );
    }
}
