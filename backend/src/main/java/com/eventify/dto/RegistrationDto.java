package com.eventify.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RegistrationDto {

    private Long id;

    @NotNull(message = "User ID should not be null")
    private Long userId;

    @NotNull(message = "Event ID should not be null")
    private Long eventId;
    
    private LocalDateTime registrationDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
