package com.cg.usermicroservice.mapper;

import com.cg.usermicroservice.dto.UserDto;
import com.cg.usermicroservice.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getMentorId(),
                user.getCourseId()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getMentorId(),
                userDto.getCourseId()
        );
        return user;
    }
}
