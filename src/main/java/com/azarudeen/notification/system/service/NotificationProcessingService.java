package com.azarudeen.notification.system.service;

import com.azarudeen.notification.system.channel.NotificationChannel;
import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class NotificationProcessingService {

    private final List<NotificationChannel> notificationChannels;
    private final NotificationRepository notificationRepository;
    @Value("${notification.retry.max-attempts}")
    private int maxRetryAttempts;

    public NotificationProcessingService(
            List<NotificationChannel> notificationChannels,
            NotificationRepository notificationRepository) {

        this.notificationChannels = notificationChannels;
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void process(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Notification not found with id: " + notificationId
                ));
        if (notification.getStatus() == NotificationStatus.SENT) {
            log.info("Skipping already processed notification with id: {}", notification.getId());
            return;
        }

        try {
            NotificationChannel channel = notificationChannels.stream()
                    .filter(item -> item.getType() == notification.getType())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Unsupported notification type: " + notification.getType()
                    ));

            channel.send(notification);
            notification.setStatus(NotificationStatus.SENT);

            notificationRepository.save(notification);

            log.info("Notification processed successfully with id: {}", notification.getId());

        } catch (Exception exception) {

            notification.setRetryCount(notification.getRetryCount() + 1);

            if (notification.getRetryCount() < maxRetryAttempts) {
                notification.setStatus(NotificationStatus.PENDING);
                notificationRepository.save(notification);

                log.warn("Notification processing failed with id: {}, retryCount: {}. Retrying...",
                        notification.getId(),
                        notification.getRetryCount(),
                        exception);

                process(notification.getId());
                return;
            }

            notification.setStatus(NotificationStatus.FAILED);
            notificationRepository.save(notification);

            log.error("Notification processing failed with id: {}, retryCount: {}. Maximum retry attempts reached.",
                    notification.getId(),
                    notification.getRetryCount(),
                    exception);
        }
    }
}