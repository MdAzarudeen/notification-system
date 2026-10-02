package com.azarudeen.notification.system.service;

import com.azarudeen.notification.system.channel.NotificationChannel;
import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.azarudeen.notification.system.channel.NotificationChannelResolver;

@Service
@Slf4j
public class NotificationProcessingService {

    private final NotificationChannelResolver notificationChannelResolver;
    private final NotificationRepository notificationRepository;
    private final NotificationRetryService notificationRetryService;

    public NotificationProcessingService(
            NotificationChannelResolver notificationChannelResolver,
            NotificationRepository notificationRepository,
            NotificationRetryService notificationRetryService) {
        this.notificationChannelResolver = notificationChannelResolver;
        this.notificationRepository = notificationRepository;
        this.notificationRetryService = notificationRetryService;
    }

    @Transactional
    public void process(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Notification not found with id: " + notificationId));
        if (notification.getStatus() == NotificationStatus.SENT) {
            log.info("Skipping already processed notification with id: {}", notification.getId());
            return;
        }
        try {
            NotificationChannel channel = notificationChannelResolver.resolve(notification.getType());
            channel.send(notification);
            notification.setStatus(NotificationStatus.SENT);
            notificationRepository.save(notification);
            log.info("Notification processed successfully with id: {}", notification.getId());
        } catch (Exception exception) {
            boolean shouldRetry = notificationRetryService.handleFailure(notification, exception);
            if (shouldRetry) {
                process(notification.getId());
            }
        }
    }
}