package com.azarudeen.notification.system.dto;

import com.azarudeen.notification.system.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateNotificationRequest(

        @NotNull
        NotificationType type,

        @NotBlank
        @Size(max = 2000)
        String message
) {
}