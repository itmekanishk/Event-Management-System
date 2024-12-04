package com.aes.eventmanagementsystem.service;

import java.util.List;

import com.aes.eventmanagementsystem.dto.NotificationDto;
import com.aes.eventmanagementsystem.model.Notification;

public interface INotificationService {

    /**
     * Save Notification into DB with notificationDto entity
     * 
     * @param notificationDto
     */
    void createNotification(NotificationDto notificationDto);

    /**
     * Save Notification into DB with notification entity
     * 
     * @param notification
     */
    void createNotification(Notification notification);

    /**
     * Find All Notifications by user's userId who created the notification
     * 
     * @param userId
     * @return Notification List
     */
    List<Notification> fetchNotificationsByUserId(int userId);

    /**
     * Find all notifications by event's eventId which is related to notification
     * 
     * @param eventId
     * @return Notification List
     */
    List<Notification> fetchNotificationsByEventId(int eventId);

    /**
     * Get All Notifications
     * 
     * @return Notification List
     */
    List<Notification> fetchAll();

    /**
     * Delete notification by notificationId
     * 
     * @param notificationId
     */
    void deleteNotifications(int notificationId);

    /**
     * Get a list which contains eventId - unread notification count for all
     * notifications
     * 
     * @return List of eventId - unread notification count pair object
     */
    List<Object[]> findEventIdsWithUnreadNotificationCounts(int userId);
}
