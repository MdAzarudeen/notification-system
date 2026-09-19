package com.azarudeen.notification.system.repository;

import com.azarudeen.notification.system.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}