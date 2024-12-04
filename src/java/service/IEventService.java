package com.aes.eventmanagementsystem.service;

import java.util.List;
import com.aes.eventmanagementsystem.dto.EventDto;
import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.model.User;

public interface IEventService {

    /**
     * Save Event into DB
     * 
     * @param eventDto
     */
    void createEvent(EventDto eventDto);

    /**
     * Fetch event from DB by eventId
     * 
     * @param eventId
     * @return
     */
    Event fetchEvent(int eventId);

    /**
     * Fetch all events from db orded by eventName
     * 
     * @return Event List
     */
    List<Event> fetchAllSortedDesc();

    /**
     * Fetch all specific event participants by eventId
     * 
     * @param eventId
     * @return Event List
     */
    List<User> fetchParticipantsByEventId(int eventId);

    /**
     * Update Event
     * 
     * @param event
     */
    void updateEvent(Event event);

    /**
     * Delete Event
     * 
     * @param eventId
     */
    void deleteEvent(int eventId);

    /**
     * 
     * Find all events which specific user participated by users userId
     * 
     * @param userId
     * @return Event List
     */
    List<Event> findEventsByUserId(int userId);

    /**
     * Find all events with userId which specific user created and Order by event
     * date
     * 
     * @param userId
     * @return Event List
     */
    List<Event> findEventsByUserIdSortedByEventDate(int userId);

    /**
     * Find all events which specific user created by users userId
     * 
     * @param userId
     * @return Event List
     */
    List<Event> findEventsByOrganizerId(int userId);

    /**
     * Find all events and participants count that who participated to each event by
     * userId who created the event
     * 
     * @param userId
     * @return A Object List that contains Event and participant count who
     *         participated to event
     */
    List<Object[]> findEventsWithParticipantsCountByUserId(int userId);

}
