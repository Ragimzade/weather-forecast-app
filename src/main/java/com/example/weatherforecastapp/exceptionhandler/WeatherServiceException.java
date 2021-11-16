package com.example.weatherforecastapp.exceptionhandler;

public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(String message) {
        super(message);
    }
}
