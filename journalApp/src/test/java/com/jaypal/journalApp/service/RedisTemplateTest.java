package com.jaypal.journalApp.service;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void testRedis() {
        try {
            redisTemplate.opsForValue().set("testKey", "HelloRedis");
            String value = redisTemplate.opsForValue().get("testKey");
            System.out.println("✅ Redis connected! Fetched value: " + value);
        } catch (Exception e) {
            System.err.println("❌ Redis connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
