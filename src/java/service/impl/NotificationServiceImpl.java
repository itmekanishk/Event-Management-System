package com.aes.eventmanagementsystem.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.aes.eventmanagementsystem.dto.NotificationDto;
import com.aes.eventmanagementsystem.exception.ResourceNotFoundExcepiton;
import com.aes.eventmanagementsystem.mapper.NotificationMapper;
import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.model.Notification;
import com.aes.eventmanagementsystem.model.User;
import com.aes.eventmanagementsystem.repository.EventRepository;
import com.aes.eventmanagementsystem.repository.NotificationRepository;
import com.aes.eventmanagementsystem.repository.UserRepository;
import com.aes.eventmanagementsystem.service.INotificationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements INotificationService {

        NotificationRepository notificationRepository;
        UserRepository userRepository;
        EventRepository eventRepository;

        /**
         * Save Notification into DB with notificationDto entity
         * 
         * @param notificationDto
         */
        @Override
        public void createNotification(NotificationDto notificationDto) {
                User user = userRepository.findById(notificationDto.getUserId()).orElseThrow(
                                () -> new ResourceNotFoundExcepiton("User", "id",
                                                String.valueOf(notificationDto.getUserId())));
                Event event = eventRepository.findById(notificationDto.getEventId());
                Notification notification = notificationRepository
                                .findByNotificationContentAndUserAndEvent(notificationDto.getNotificationContent(),
                                                user, event);
                // todo send comm
                if (notification == null)
                        notification = NotificationMapper.mapToNotification(notificationDto, new Notification());
                notification.setRead(false);
                notification.setUser(user);
                notification.setEvent(event);
                Notification savedNotification = notificationRepository.save(notification);
        }

        /**
         * Find All Notifications by user's userId who created the notification
         * 
         * @param userId
         * @return Notification List
         */
        @Override
        public List<Notification> fetchNotificationsByUserId(int userId) {
                List<Notification> notificationList = notificationRepository.findNotificationsByUserId(userId);
                return notificationList;
        }

        /**
         * Get All Notifications
         * 
         * @return Notification List
         */
        @Override
        public List<Notification> fetchAll() {
                return notificationRepository.findAll();
        }

        /**
         * Save Notification into DB with notification entity
         * 
         * @param notification
         */
        @Override
        public void createNotification(Notification notification) {
                notificationRepository.save(notification);
        }

        /**
         * Delete notification by notificationId
         * 
         * @param notificationId
         */
        @Override
        public void deleteNotifications(int notificationId) {
                notificationRepository.delete(notificationRepository.findById(notificationId)
                                .orElseThrow(() -> new ResourceNotFoundExcepiton("Notification", "notificationId",
                                                String.valueOf(notificationId))));
        }

        /**
         * Find all notifications by event's eventId which is related to notification
         * 
         * @param eventId
         * @return Notification List
         */
        @Override
        public List<Notification> fetchNotificationsByEventId(int eventId) {
                List<Notification> notificationList = notificationRepository.findNotificationsByEventId(eventId);
                return notificationList;
        }

        /**
         * Get a list which contains eventId - unread notification count for all
         * notifications
         * 
         * @return List of eventId - unread notification count pair object
         */
        @Override
        public List<Object[]> findEventIdsWithUnreadNotificationCounts(int userId) {
                return notificationRepository.findEventIdsWithUnreadNotificationCounts(userId);
        }

}
