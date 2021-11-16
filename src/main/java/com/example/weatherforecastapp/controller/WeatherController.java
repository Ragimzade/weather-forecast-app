package com.example.weatherforecastapp.controller;

import com.example.weatherforecastapp.dto.WeatherDTO;
import com.example.weatherforecastapp.model.response.WeatherResponse;
import com.example.weatherforecastapp.service.WeatherForecastService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping(
            path = "/{city}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public WeatherResponse getWeatherForCity(@PathVariable String city) {
        var weather = new WeatherResponse();
        WeatherDTO weatherDTO = weatherForecastService.getWeatherForCity(city);
        BeanUtils.copyProperties(weatherDTO, weather);
        return weather;
    }
}
