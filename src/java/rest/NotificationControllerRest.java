package com.aes.eventmanagementsystem.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aes.eventmanagementsystem.constants.NotificationConstants;
import com.aes.eventmanagementsystem.dto.NotificationDto;
import com.aes.eventmanagementsystem.dto.ResponseDto;
import com.aes.eventmanagementsystem.model.Notification;
import com.aes.eventmanagementsystem.service.INotificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class NotificationControllerRest {

    INotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody NotificationDto notificationDto) {
        notificationService.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(NotificationConstants.STATUS_201, NotificationConstants.MESSAGE_201));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> fetchNotificationsByUserId(@PathVariable(name = "userId") int userId) {

        // todo get rid of some data if necessary
        List<Notification> notificationList = notificationService.fetchNotificationsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(notificationList);
    }

    @GetMapping("/getNotifications/{eventId}")
    @ResponseBody
    public List<Notification> getNotifications(@PathVariable int eventId) {
        return notificationService.fetchNotificationsByEventId(eventId);
    }
}
