package com.eventify.service.impl;

import com.eventify.dto.EventDto;
import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.exception.ResourceNotFoundException;
import com.eventify.repository.EventRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        User organizer = userRepository.findById(eventDto.getOrganizerId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id: " + eventDto.getOrganizerId()));

        Event event = mapToEntity(eventDto);
        event.setOrganizer(organizer);

        Event savedEvent = eventRepository.save(event);
        return mapToDto(savedEvent);
    }

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        return mapToDto(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByType(String type) {
        List<Event> events = eventRepository.findByType(type);
        return events.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByOrganizer(Long organizerId) {
        List<Event> events = eventRepository.findByOrganizerId(organizerId);
        return events.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(Long eventId, EventDto eventDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        event.setName(eventDto.getName());
        event.setType(eventDto.getType());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());

        Event updatedEvent = eventRepository.save(event);
        return mapToDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event not found with id: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<EventDto> searchEvents(String keyword) {
        List<Event> events = eventRepository.searchEvents(keyword);
        return events.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private EventDto mapToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setType(event.getType());
        eventDto.setDate(event.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setOrganizerId(event.getOrganizer().getId());
        eventDto.setOrganizerName(event.getOrganizer().getUsername());
        return eventDto;
    }

    private Event mapToEntity(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setType(eventDto.getType());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        return event;
    }
}
