package com.eventify.service;

import com.eventify.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto feedbackDto);
    FeedbackDto getFeedbackById(Long feedbackId);
    List<FeedbackDto> getFeedbackByEventId(Long eventId);
    List<FeedbackDto> getFeedbackByUserId(Long userId);
    void deleteFeedback(Long feedbackId);
}
