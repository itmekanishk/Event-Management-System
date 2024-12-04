package com.aes.eventmanagementsystem.mapper;

import com.aes.eventmanagementsystem.dto.NotificationDto;
import com.aes.eventmanagementsystem.model.Notification;

public class NotificationMapper {
    public static NotificationDto mapTonotificationDto(Notification notification,
            NotificationDto notificationDto) {
        notificationDto.setNotificationContent(notification.getNotificationContent());
        notificationDto.setUserId(notification.getUser().getUserId());
        notificationDto.setEventId(notification.getEvent().getEventId());
        return notificationDto;
    }

    public static Notification mapToNotification(NotificationDto notificationDto, Notification notification) {
        notification.setNotificationContent(notificationDto.getNotificationContent());
        return notification;
    }
}
