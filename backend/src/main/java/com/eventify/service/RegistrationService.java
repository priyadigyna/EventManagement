package com.eventify.service;

import com.eventify.dto.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto createRegistration(RegistrationDto registrationDto);
    RegistrationDto getRegistrationById(Long registrationId);
    List<RegistrationDto> getRegistrationsByUserId(Long userId);
    List<RegistrationDto> getRegistrationsByEventId(Long eventId);
    void deleteRegistration(Long registrationId);
}
