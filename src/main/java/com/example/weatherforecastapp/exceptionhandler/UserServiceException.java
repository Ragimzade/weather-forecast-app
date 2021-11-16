package com.example.weatherforecastapp.exceptionhandler;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message) {
        super(message);
    }
}
