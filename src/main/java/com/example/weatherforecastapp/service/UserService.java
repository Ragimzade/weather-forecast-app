package com.example.weatherforecastapp.service;

import com.example.weatherforecastapp.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO createUser(UserDTO user);

    UserDTO getUser(String email);

    UserDTO getUserByUserId(String id);

    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}
