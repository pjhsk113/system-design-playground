package study.playground.springboot.notification;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FcmMock implements SendNotification {
    @Override
    public void send(List<String> contents) {
        // do nothing
    }
}
