package com.azarudeen.notification.system.service;

import com.azarudeen.notification.system.event.NotificationCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessingListener {

    private final NotificationProcessingService notificationProcessingService;

    @Async("notificationTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleNotificationCreated(NotificationCreatedEvent event) {
        log.info("Starting async processing for notification id: {}", event.notificationId());
        notificationProcessingService.process(event.notificationId());
        log.info("Completed async processing for notification id: {}", event.notificationId());
    }
}