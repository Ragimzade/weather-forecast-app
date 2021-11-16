package com.example.weatherforecastapp.service.impl;


import com.example.weatherforecastapp.Utils;
import com.example.weatherforecastapp.dto.UserDTO;
import com.example.weatherforecastapp.entity.UserEntity;
import com.example.weatherforecastapp.exceptionhandler.ErrorMessages;
import com.example.weatherforecastapp.exceptionhandler.UserServiceException;
import com.example.weatherforecastapp.repository.UserRepository;
import com.example.weatherforecastapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO user) {
        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateRandomString(30));

        UserDTO returnValue = new UserDTO();
        UserEntity storedUserDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO getUser(String email) {
        var userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUserByUserId(String id) {

        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new UserServiceException("User with ID: {%s} not found".formatted(id));
        }
        BeanUtils.copyProperties(userEntity, userDTO);

        return userDTO;
    }

    @Override
    public UserDTO updateUser(String id, UserDTO user) {
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUser, userDTO);
        return userDTO;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
