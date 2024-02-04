package study.playground.springboot.notification;

import java.util.List;

public interface SendNotification {
    void send(List<String> contents);
}
