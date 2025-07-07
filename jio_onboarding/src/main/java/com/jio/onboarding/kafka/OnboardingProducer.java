package com.jio.onboarding.kafka;

import com.jio.onboarding.entity.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OnboardingProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public OnboardingProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOnboardingEvent(User user) {
        kafkaTemplate.send("user.onboarded", user.getId().toString(), user);
    }
}
