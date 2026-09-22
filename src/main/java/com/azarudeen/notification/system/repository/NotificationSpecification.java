package com.azarudeen.notification.system.repository;

import com.azarudeen.notification.system.entity.Notification;
import com.azarudeen.notification.system.enums.NotificationStatus;
import com.azarudeen.notification.system.enums.NotificationType;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSpecification {

    public static Specification<Notification> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) ->
                userId == null
                        ? null
                        : criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification<Notification> hasStatus(NotificationStatus status) {
        return (root, query, criteriaBuilder) ->
                status == null
                        ? null
                        : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Notification> hasType(NotificationType type) {
        return (root, query, criteriaBuilder) ->
                type == null
                        ? null
                        : criteriaBuilder.equal(root.get("type"), type);
    }
}