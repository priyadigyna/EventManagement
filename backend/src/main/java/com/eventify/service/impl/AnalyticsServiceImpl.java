package com.eventify.service.impl;

import com.eventify.dto.AnalyticsDto;
import com.eventify.repository.EventRepository;
import com.eventify.repository.RegistrationRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public AnalyticsDto getGeneralAnalytics() {
        long totalUsers = userRepository.count();
        long totalEvents = eventRepository.count();
        long totalRegistrations = registrationRepository.count();

        AnalyticsDto analyticsDto = new AnalyticsDto();
        analyticsDto.setTotalUsers(totalUsers);
        analyticsDto.setTotalEvents(totalEvents);
        analyticsDto.setTotalRegistrations(totalRegistrations);

        return analyticsDto;
    }
}
