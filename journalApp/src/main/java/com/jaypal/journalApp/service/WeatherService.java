package com.jaypal.journalApp.service;

import com.jaypal.journalApp.apiResponse.WeatherResponse;
import com.jaypal.journalApp.cache.AppCache;
import com.jaypal.journalApp.constants.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {


    @Value("${weather.api.key}")
    private String apikey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;


    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather of " + city, WeatherResponse.class);

        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholder.CITY, city).replace(Placeholder.API_KEY, apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("Weather of " + city, body, 300l);
            }
            return body;
        }
    }

}
