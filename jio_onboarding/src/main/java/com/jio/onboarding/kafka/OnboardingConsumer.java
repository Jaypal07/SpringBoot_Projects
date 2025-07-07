package com.jio.onboarding.kafka;

import com.jio.onboarding.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OnboardingConsumer {
    private static final Logger log = LoggerFactory.getLogger(OnboardingConsumer.class);

    @KafkaListener(topics = "user.onboarded", groupId = "onboarding-service")
    public void consume(ConsumerRecord<String, User> record) {
        log.info("Received onboarding event for user {}", record.value().getEmail());
        // handle event (send email, etc.)
    }
}
