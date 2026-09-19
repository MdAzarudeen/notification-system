package com.azarudeen.notification.system.dto;

import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.enums.NotificationType;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        Long userId,
        NotificationType type,
        String message,
        NotificationStatus status,
        LocalDateTime createdAt
) {
}