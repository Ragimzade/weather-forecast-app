package com.example.weatherforecastapp.service.impl;

import com.example.weatherforecastapp.dto.WeatherDTO;
import com.example.weatherforecastapp.entity.LocationEntity;
import com.example.weatherforecastapp.exceptionhandler.WeatherServiceException;
import com.example.weatherforecastapp.repository.WeatherRepository;
import com.example.weatherforecastapp.service.WeatherForecastService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public WeatherDTO getWeatherForCity(String city) {

        LocationEntity location = weatherRepository.findByCity(city);

        if (location == null) throw new WeatherServiceException("City '%s' is not found".formatted(city));
        WeatherDTO returnValue = new WeatherDTO();
        returnValue.setToday(location.getWeatherEntity().getTomorrow());
        returnValue.setTomorrow(location.getWeatherEntity().getToday());
        BeanUtils.copyProperties(location, returnValue);
        return returnValue;
    }
}
