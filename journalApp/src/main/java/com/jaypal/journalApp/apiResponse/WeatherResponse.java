package com.jaypal.journalApp.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current {

        private int temperature;
        private int feelslike;
    }

}

