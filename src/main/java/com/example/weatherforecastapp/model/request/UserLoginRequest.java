package com.example.weatherforecastapp.model.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String email;
    private String password;

}
