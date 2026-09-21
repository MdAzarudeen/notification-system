package com.azarudeen.notification.system.channel;

import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsNotificationChannel implements NotificationChannel {

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    public void send(Notification notification) {
        log.info("Sending SMS notification for userId: {}", notification.getUserId());
    }
}