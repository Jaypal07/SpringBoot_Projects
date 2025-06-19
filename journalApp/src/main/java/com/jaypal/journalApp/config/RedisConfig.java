package com.jaypal.journalApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class for connecting to Redis using Spring Data Redis.
 * It sets up the connection factory and RedisTemplate with proper serialization.
 */
@Configuration
public class RedisConfig {

    // Injecting Redis connection properties from application.yml or application.properties
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.username}")
    private String redisUsername; // Redis Cloud usually uses 'default' as username

//    @Value("${spring.redis.ssl}")
//    private boolean redisSsl; // Uncomment if SSL is needed

    /**
     * Creates a RedisConnectionFactory using Lettuce (non-blocking Redis client).
     * Connects to a standalone Redis server with configured host, port, username, and password.
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setPassword(redisPassword);
        config.setUsername(redisUsername); // Required for Redis Cloud and secured Redis instances

        // You can enable SSL by uncommenting below line if needed
        // LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
        // factory.setUseSsl(redisSsl);

        return new LettuceConnectionFactory(config);
    }

    /**
     * Defines a RedisTemplate bean for interacting with Redis.
     * Configures serializers to convert keys/values to String for consistency and readability.
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();

        // String serializer for keys and values (instead of default Java serialization)
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(serializer);          // Key: String
        template.setValueSerializer(serializer);        // Value: String
        template.setHashKeySerializer(serializer);      // Hash Key: String
        template.setHashValueSerializer(serializer);    // Hash Value: String

        template.afterPropertiesSet(); // Ensure the template is fully initialized
        return template;
    }
}
