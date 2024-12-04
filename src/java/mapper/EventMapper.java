package com.aes.eventmanagementsystem.mapper;

import com.aes.eventmanagementsystem.dto.EventDto;
import com.aes.eventmanagementsystem.model.Event;

public class EventMapper {

    public static EventDto mapToEventDto(Event event, EventDto eventDto) {
        eventDto.setEventName(event.getEventName());
        eventDto.setEventLocation(event.getEventLocation());
        eventDto.setEventDate(event.getEventDate());
        return eventDto;
    }

    public static Event mapToEvent(EventDto eventDto, Event event) {
        event.setEventName(eventDto.getEventName());
        event.setEventLocation(eventDto.getEventLocation());
        event.setEventDate(eventDto.getEventDate());
        return event;
    }
}
