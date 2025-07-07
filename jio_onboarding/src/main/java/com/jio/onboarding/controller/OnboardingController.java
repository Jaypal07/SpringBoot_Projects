package com.jio.onboarding.controller;

import com.jio.onboarding.entity.User;
import com.jio.onboarding.service.OnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/onboarding")
public class OnboardingController {

    private final OnboardingService service;

    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> onboard(@RequestBody User user) {
        User onboarded = service.onboardUser(user);
        return ResponseEntity.ok(onboarded);
    }
}
