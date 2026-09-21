package com.azarudeen.notification.system.channel;

import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PushNotificationChannel implements NotificationChannel {

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }

    @Override
    public void send(Notification notification) {
        log.info("Sending PUSH notification for userId: {}", notification.getUserId());
    }
}