package com.eventify.service.impl;

import com.eventify.dto.FeedbackDto;
import com.eventify.entity.Event;
import com.eventify.entity.Feedback;
import com.eventify.entity.User;
import com.eventify.exception.ResourceNotFoundException;
import com.eventify.repository.EventRepository;
import com.eventify.repository.FeedbackRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        User user = userRepository.findById(feedbackDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + feedbackDto.getUserId()));

        Event event = eventRepository.findById(feedbackDto.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + feedbackDto.getEventId()));

        Feedback feedback = mapToEntity(feedbackDto);
        feedback.setUser(user);
        feedback.setEvent(event);

        Feedback savedFeedback = feedbackRepository.save(feedback);
        return mapToDto(savedFeedback);
    }

    @Override
    public FeedbackDto getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + feedbackId));
        return mapToDto(feedback);
    }

    @Override
    public List<FeedbackDto> getFeedbackByEventId(Long eventId) {
        List<Feedback> feedbacks = feedbackRepository.findByEventId(eventId);
        return feedbacks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDto> getFeedbackByUserId(Long userId) {
        List<Feedback> feedbacks = feedbackRepository.findByUserId(userId);
        return feedbacks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new ResourceNotFoundException("Feedback not found with id: " + feedbackId);
        }
        feedbackRepository.deleteById(feedbackId);
    }

    private FeedbackDto mapToDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setUserId(feedback.getUser().getId());
        feedbackDto.setEventId(feedback.getEvent().getId());
        feedbackDto.setRating(feedback.getRating());
        feedbackDto.setReview(feedback.getReview());
        return feedbackDto;
    }

    private Feedback mapToEntity(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setRating(feedbackDto.getRating());
        feedback.setReview(feedbackDto.getReview());
        return feedback;
    }
}
