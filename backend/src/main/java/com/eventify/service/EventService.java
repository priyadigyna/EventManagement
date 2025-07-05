package com.eventify.service;

import com.eventify.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);
    EventDto getEventById(Long eventId);
    List<EventDto> getAllEvents();
    List<EventDto> getEventsByType(String type);
    List<EventDto> getEventsByOrganizer(Long organizerId);
    EventDto updateEvent(Long eventId, EventDto eventDto);
    void deleteEvent(Long eventId);

    List<EventDto> searchEvents(String keyword);
}
