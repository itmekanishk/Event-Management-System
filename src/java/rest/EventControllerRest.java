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
import org.springframework.web.bind.annotation.RestController;

import com.aes.eventmanagementsystem.constants.EventConstants;
import com.aes.eventmanagementsystem.dto.EventDto;
import com.aes.eventmanagementsystem.dto.ResponseDto;
import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.service.IEventService;

@RestController
@RequestMapping(path = "/api/events", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EventControllerRest {

    private IEventService eventService;

    public EventControllerRest(IEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(EventConstants.STATUS_201, EventConstants.MESSAGE_201));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> fetchEvent(@PathVariable(name = "eventId") int eventId) {
        Event event = eventService.fetchEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<Event>> fetchEventByUserId(@PathVariable(name =
    // "userId") int userId) {
    // // List<Event> eventList = eventService.fetchEventByUserId(userId);
    // return ResponseEntity.status(HttpStatus.OK).body(eventList);
    // }

    // @GetMapping("/fetch")
    // public ResponseEntity<UserDto> fetchUserDetails(@RequestParam String email) {
    // UserDto userDto = userService.fetchUser(email);
    // return ResponseEntity.status(HttpStatus.OK).body(userDto);
    // }

    // @PutMapping("/update")
    // public ResponseEntity<ResponseDto> updateUserDetails(@RequestBody UserDto
    // userDto) {

    // boolean isUpdated = userService.updateUser(userDto);

    // if (isUpdated) {
    // return ResponseEntity.status(HttpStatus.OK)
    // .body(new ResponseDto(UserConstants.STATUS_200,
    // UserConstants.MESSAGE_200));
    // } else {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body(new ResponseDto(UserConstants.STATUS_417,
    // UserConstants.MESSAGE_417_UPDATE));
    // }
    // }

}
