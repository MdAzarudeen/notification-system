package com.azarudeen.notification.system.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notification.retry")
public class NotificationRetryProperties {

    private int maxAttempts;
}