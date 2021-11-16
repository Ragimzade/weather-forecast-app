package com.example.weatherforecastapp.controller;

import com.example.weatherforecastapp.dto.UserDTO;
import com.example.weatherforecastapp.exceptionhandler.ErrorMessages;
import com.example.weatherforecastapp.exceptionhandler.UserServiceException;
import com.example.weatherforecastapp.model.request.UserDetailsRequest;
import com.example.weatherforecastapp.model.response.UserResponse;
import com.example.weatherforecastapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserResponse createUser(@RequestBody UserDetailsRequest userDetails) {
        var user = new UserResponse();

        if (userDetails.getFirstName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        var userDto = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDTO createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, user);
        return user;
    }
}
