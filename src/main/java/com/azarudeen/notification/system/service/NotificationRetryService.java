package com.azarudeen.notification.system.service;

import com.azarudeen.notification.system.config.NotificationRetryProperties;
import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationRetryService {

    private final NotificationRepository notificationRepository;
    private final NotificationRetryProperties notificationRetryProperties;

    public NotificationRetryService(
            NotificationRepository notificationRepository,
            NotificationRetryProperties notificationRetryProperties) {
        this.notificationRepository = notificationRepository;
        this.notificationRetryProperties = notificationRetryProperties;
    }

    public boolean handleFailure(Notification notification, Exception exception) {
        notification.setRetryCount(notification.getRetryCount() + 1);
        if (notification.getRetryCount() < notificationRetryProperties.getMaxAttempts()) {
            notification.setStatus(NotificationStatus.PENDING);
            notificationRepository.save(notification);
            log.warn("Notification processing failed with id: {}, retryCount: {}. Retrying...",
                    notification.getId(), notification.getRetryCount(), exception);
            return true;
        }
        notification.setStatus(NotificationStatus.FAILED);
        notificationRepository.save(notification);
        log.error(
                "Notification processing failed with id: {}, retryCount: {}. Maximum retry attempts reached.",
                notification.getId(), notification.getRetryCount(), exception);
        return false;
    }
}