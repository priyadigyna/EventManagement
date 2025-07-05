package com.eventify.controller;

import com.eventify.dto.RegistrationDto;
import com.eventify.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(@Valid @RequestBody RegistrationDto registrationDto) {
        RegistrationDto createdRegistration = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable("id") Long registrationId) {
        RegistrationDto registrationDto = registrationService.getRegistrationById(registrationId);
        return ResponseEntity.ok(registrationDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByUserId(@PathVariable("id") Long userId) {
        List<RegistrationDto> registrations = registrationService.getRegistrationsByUserId(userId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByEventId(@PathVariable("id") Long eventId) {
        List<RegistrationDto> registrations = registrationService.getRegistrationsByEventId(eventId);
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable("id") Long registrationId) {
        registrationService.deleteRegistration(registrationId);
        return ResponseEntity.ok("Registration deleted successfully!");
    }
}
