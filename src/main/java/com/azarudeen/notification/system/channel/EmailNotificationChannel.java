package com.azarudeen.notification.system.channel;

import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationChannel implements NotificationChannel {

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public void send(Notification notification) {
//        log.info("Sending EMAIL notification for userId: {}", notification.getUserId());
        throw new RuntimeException("Test notification failure");
    }
}