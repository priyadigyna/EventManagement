package com.eventify.controller;

import com.eventify.dto.AnalyticsDto;
import com.eventify.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summary")
    public ResponseEntity<AnalyticsDto> getAnalyticsSummary() {
        return ResponseEntity.ok(analyticsService.getGeneralAnalytics());
    }
}
