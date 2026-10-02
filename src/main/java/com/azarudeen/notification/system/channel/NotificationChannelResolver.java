package com.azarudeen.notification.system.channel;

import com.azarudeen.notification.system.enums.NotificationType;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NotificationChannelResolver {

    private final Map<NotificationType, NotificationChannel> channelMap;

    public NotificationChannelResolver(List<NotificationChannel> channels) {
        this.channelMap = channels.stream().collect(Collectors.toMap(
                        NotificationChannel::getType, Function.identity()));}

    public NotificationChannel resolve(NotificationType type) {
        NotificationChannel channel = channelMap.get(type);
        if (channel == null) {
            throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
        return channel;
    }
}