package com.cg.usermicroservice.service.impl;

import com.cg.usermicroservice.dto.*;
import com.cg.usermicroservice.entity.User;
import com.cg.usermicroservice.mapper.AutoUserMapper;
import com.cg.usermicroservice.mapper.UserMapper;
import com.cg.usermicroservice.repository.UserRepository;
import com.cg.usermicroservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RestTemplate restTemplate;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public APIResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        ResponseEntity<MentorDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/mentors/" + user.getMentorId(),
                MentorDto.class);
        MentorDto mentorDto = responseEntity.getBody();

        ResponseEntity<CourseDto> responseEntity1 = restTemplate.getForEntity("http://localhost:8081/api/courses/" + user.getCourseId(),
                CourseDto.class);
        CourseDto courseDto = responseEntity1.getBody();

        UserDto userDto = UserMapper.mapToUserDto(user);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setUser(userDto);
        apiResponseDto.setMentor(mentorDto);
        apiResponseDto.setCourse(courseDto);

        return apiResponseDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(updatedUserDto.getName());
            existingUser.setEmail(updatedUserDto.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }
}