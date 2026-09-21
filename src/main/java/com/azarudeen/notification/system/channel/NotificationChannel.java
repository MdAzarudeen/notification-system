package com.azarudeen.notification.system.channel;

import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationType;

public interface NotificationChannel {

    NotificationType getType();

    void send(Notification notification);
}