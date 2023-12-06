package com.cg.usermicroservice.service;

import com.cg.usermicroservice.dto.APIResponseDto;
import com.cg.usermicroservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    APIResponseDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId , UserDto userDto);

    void deleteUser(Long userId);
}
