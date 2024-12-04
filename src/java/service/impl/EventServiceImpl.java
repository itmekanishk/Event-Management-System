package com.aes.eventmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aes.eventmanagementsystem.dto.EventDto;
import com.aes.eventmanagementsystem.exception.ResourceNotFoundExcepiton;
import com.aes.eventmanagementsystem.exception.DataAlreadyExistsException;
import com.aes.eventmanagementsystem.mapper.EventMapper;
import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.model.User;
import com.aes.eventmanagementsystem.repository.EventRepository;
import com.aes.eventmanagementsystem.repository.UserRepository;
import com.aes.eventmanagementsystem.service.IEventService;

@Service
public class EventServiceImpl implements IEventService {

    Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    private EventRepository eventRepository;
    private UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save Event into DB
     * 
     * @param eventDto
     */
    @Override
    public void createEvent(EventDto eventDto) {

        Event event = EventMapper.mapToEvent(eventDto, new Event());

        Optional<Event> optionalEvent = eventRepository.findByEventNameAndEventDateAndEventLocation(
                eventDto.getEventName(), eventDto.getEventDate(), eventDto.getEventLocation());
        if (optionalEvent.isPresent()) {
            throw new DataAlreadyExistsException("Event already exists with given infos %s : %s : %s"
                    .formatted(eventDto.getEventName(), eventDto.getEventDate(), eventDto.getEventLocation()));
        }

        // todo send comm
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExcepiton("Event", "email", email));
        event.setUser(user);

        Event savedEvent = eventRepository.save(event);
    }

    /**
     * Fetch event from DB by eventId
     * 
     * @param eventId
     * @return
     */
    @Override
    public Event fetchEvent(int eventId) {
        Event event = eventRepository.findById(eventId);

        return event;
    }

    /**
     * Fetch all events from db orded by eventName
     * 
     * @return Event List
     */
    @Override
    public List<Event> fetchAllSortedDesc() {
        return eventRepository.findAll(Sort.by("eventName").descending());
    }

    /**
     * Fetch all specific event participants by eventId
     * 
     * @param eventId
     * @return Event List
     */
    @Override
    public List<User> fetchParticipantsByEventId(int eventId) {
        return eventRepository.findParticipantsByEventId(eventId);
    }

    /**
     * Update Event
     * 
     * @param event
     */
    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    /**
     * Delete Event
     * 
     * @param eventId
     */
    @Override
    public void deleteEvent(int eventId) {
        Event event = eventRepository.findById(eventId);

        eventRepository.delete(event);
    }

    /**
     * 
     * Find all events which specific user participated by users userId
     * 
     * @param userId
     * @return Event List
     */
    @Override
    public List<Event> findEventsByUserId(int userId) {
        return eventRepository.findEventsByUserId(userId);
    }

    /**
     * Find all events which specific user created by users userId
     * 
     * @param userId
     * @return Event List
     */
    @Override
    public List<Event> findEventsByOrganizerId(int userId) {
        return eventRepository.findEventsByOrganizerUserId(userId);
    }

    /**
     * Find all events and participants count that who participated to each event by
     * userId who created the event
     * 
     * @param userId
     * @return A Object List that contains Event and participant count who
     *         participated to event
     */
    @Override
    public List<Object[]> findEventsWithParticipantsCountByUserId(int userId) {
        return eventRepository.findEventsWithParticipantsCountByUserId(userId);
    }

    /**
     * Find all events with userId which specific user created and Order by event
     * date
     * 
     * @param userId
     * @return Event List
     */
    @Override
    public List<Event> findEventsByUserIdSortedByEventDate(int userId) {
        return eventRepository.findEventsByEventIdSortedByEventDate(userId);
    }
}
