package com.eventify.service.impl;

import com.eventify.dto.RegistrationDto;
import com.eventify.entity.Event;
import com.eventify.entity.Registration;
import com.eventify.entity.User;
import com.eventify.exception.ResourceNotFoundException;
import com.eventify.repository.EventRepository;
import com.eventify.repository.RegistrationRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        User user = userRepository.findById(registrationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + registrationDto.getUserId()));

        Event event = eventRepository.findById(registrationDto.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + registrationDto.getEventId()));

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegistrationDate(LocalDateTime.now());

        Registration savedRegistration = registrationRepository.save(registration);
        return mapToDto(savedRegistration);
    }

    @Override
    public RegistrationDto getRegistrationById(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + registrationId));
        return mapToDto(registration);
    }

    @Override
    public List<RegistrationDto> getRegistrationsByUserId(Long userId) {
        List<Registration> registrations = registrationRepository.findByUserId(userId);
        return registrations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<RegistrationDto> getRegistrationsByEventId(Long eventId) {
        List<Registration> registrations = registrationRepository.findByEventId(eventId);
        return registrations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteRegistration(Long registrationId) {
        if (!registrationRepository.existsById(registrationId)) {
            throw new ResourceNotFoundException("Registration not found with id: " + registrationId);
        }
        registrationRepository.deleteById(registrationId);
    }

    private RegistrationDto mapToDto(Registration registration) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(registration.getId());
        registrationDto.setUserId(registration.getUser().getId());
        registrationDto.setEventId(registration.getEvent().getId());
        registrationDto.setRegistrationDate(registration.getRegistrationDate());
        return registrationDto;
    }
}
