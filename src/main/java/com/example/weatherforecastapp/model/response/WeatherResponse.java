package com.example.weatherforecastapp.model.response;

import lombok.Data;

@Data
public class WeatherResponse {

    private String today;
    private String tomorrow;
    private String city;
}
