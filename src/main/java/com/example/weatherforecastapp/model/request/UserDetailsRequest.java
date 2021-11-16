package com.example.weatherforecastapp.model.request;

import lombok.Data;

@Data
public class UserDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
