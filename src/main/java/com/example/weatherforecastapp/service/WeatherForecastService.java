package com.example.weatherforecastapp.service;

import com.example.weatherforecastapp.dto.WeatherDTO;

public interface WeatherForecastService {

    WeatherDTO getWeatherForCity(String city);
}
