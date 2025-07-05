package com.eventify.controller;

import com.eventify.dto.FeedbackDto;
import com.eventify.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        FeedbackDto createdFeedback = feedbackService.createFeedback(feedbackDto);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("id") Long feedbackId) {
        FeedbackDto feedbackDto = feedbackService.getFeedbackById(feedbackId);
        return ResponseEntity.ok(feedbackDto);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByEventId(@PathVariable("id") Long eventId) {
        List<FeedbackDto> feedback = feedbackService.getFeedbackByEventId(eventId);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByUserId(@PathVariable("id") Long userId) {
        List<FeedbackDto> feedback = feedbackService.getFeedbackByUserId(userId);
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok("Feedback deleted successfully!");
    }
}
