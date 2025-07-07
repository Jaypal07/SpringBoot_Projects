package com.jio.onboarding.service;

import com.jio.onboarding.entity.User;
import com.jio.onboarding.repository.UserRepository;
import com.jio.onboarding.kafka.OnboardingProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingService {

    private final UserRepository userRepository;
    private final OnboardingProducer producer;

    public OnboardingService(UserRepository userRepository, OnboardingProducer producer) {
        this.userRepository = userRepository;
        this.producer = producer;
    }

    @Transactional
    public User onboardUser(User user) {
        User saved = userRepository.save(user);
        producer.sendOnboardingEvent(saved);
        return saved;
    }
}
