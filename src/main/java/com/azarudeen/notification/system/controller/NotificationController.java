package com.azarudeen.notification.system.controller;

import com.azarudeen.notification.system.dto.CreateNotificationRequest;
import com.azarudeen.notification.system.dto.NotificationResponse;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.enums.NotificationType;
import com.azarudeen.notification.system.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.azarudeen.notification.system.dto.UpdateNotificationRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationService.createNotification(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNotificationRequest request) {

        return ResponseEntity.ok(
                notificationService.updateNotification(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/retry")
    public ResponseEntity<Void> retryNotification(@PathVariable Long id) {

        notificationService.retryNotification(id);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByStatus(
            @PathVariable NotificationStatus status) {

        return ResponseEntity.ok(notificationService.getNotificationsByStatus(status));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByType(
            @PathVariable NotificationType type) {

        return ResponseEntity.ok(notificationService.getNotificationsByType(type));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<NotificationResponse>> getNotifications(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        return ResponseEntity.ok(notificationService.getNotifications(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<NotificationResponse>> filterNotifications(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) NotificationStatus status,
            @RequestParam(required = false) NotificationType type,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        return ResponseEntity.ok(
                notificationService.filterNotifications(
                        userId,
                        status,
                        type,
                        pageable
                )
        );
    }
}
