package com.azarudeen.notification.system.service;

import com.azarudeen.notification.system.dto.CreateNotificationRequest;
import com.azarudeen.notification.system.dto.NotificationResponse;
import com.azarudeen.notification.system.dto.UpdateNotificationRequest;
import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.event.NotificationCreatedEvent;
import com.azarudeen.notification.system.exception.NotificationNotFoundException;
import com.azarudeen.notification.system.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationProcessingService notificationProcessingService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public NotificationResponse createNotification(CreateNotificationRequest request) {

        log.info("Creating notification for userId: {}", request.userId());

        Notification notification = Notification.builder()
                .userId(request.userId())
                .type(request.type())
                .message(request.message())
                .status(NotificationStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        log.info("Notification created successfully with id: {}", savedNotification.getId());
        eventPublisher.publishEvent(new NotificationCreatedEvent(savedNotification.getId()));
        return notificationMapper.toResponse(savedNotification);
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getAllNotifications() {

        log.info("Fetching all notifications");

        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public NotificationResponse getNotificationById(Long id) {

        log.info("Fetching notification with id: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Notification not found with id: {}", id);
                    return new NotificationNotFoundException(id);
                });

        return notificationMapper.toResponse(notification);
    }

    @Transactional
    public NotificationResponse updateNotification(
            Long id,
            UpdateNotificationRequest request) {

        log.info("Updating notification with id: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Notification not found with id: {}", id);
                    return new NotificationNotFoundException(id);
                });

        notification.setType(request.type());
        notification.setMessage(request.message());

        Notification updatedNotification = notificationRepository.save(notification);

        log.info("Notification updated successfully with id: {}", id);

        return notificationMapper.toResponse(updatedNotification);
    }

    @Transactional
    public void deleteNotification(Long id) {

        log.info("Deleting notification with id: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Notification not found with id: {}", id);
                    return new NotificationNotFoundException(id);
                });

        notificationRepository.delete(notification);

        log.info("Notification deleted successfully with id: {}", id);
    }

    @Transactional
    public void retryNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));

        if (notification.getStatus() != NotificationStatus.FAILED) {
            throw new IllegalStateException(
                    "Only failed notifications can be retried"
            );
        }
        notification.setRetryCount(0);
        notification.setStatus(NotificationStatus.PENDING);
        notificationRepository.save(notification);

        eventPublisher.publishEvent(
                new NotificationCreatedEvent(notification.getId())
        );

        log.info("Retry requested for notification id: {}", notification.getId());
    }
}