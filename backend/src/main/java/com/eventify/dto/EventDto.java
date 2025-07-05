package com.eventify.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class EventDto {

    private Long id;

    @NotEmpty(message = "Event name should not be empty")
    private String name;

    @NotEmpty(message = "Event type should not be empty")
    private String type;

    @NotNull(message = "Event date should not be null")
    @Future(message = "Event date must be in the future")
    private LocalDateTime date;

    @NotEmpty(message = "Event description should not be empty")
    private String description;

    @NotNull(message = "Organizer ID should not be null")
    private Long organizerId;
    
    private String organizerName;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }
}
