package com.cg.usermicroservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.cg.usermicroservice.dto.APIResponseDto;
import com.cg.usermicroservice.dto.CourseDto;
import com.cg.usermicroservice.dto.MentorDto;
import com.cg.usermicroservice.dto.UserDto;
import com.cg.usermicroservice.entity.User;
import com.cg.usermicroservice.repository.UserRepository;
import com.cg.usermicroservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void saveUser_Test() {
        UserDto userDto = new UserDto(1L, "shabeena", "shabee@gmail.com", 1L, 1L);
        User mockUser = new User(1L, "shabeena", "shabee@gmail.com", 1L, 1L);
        when(userRepository.save(any())).thenReturn(mockUser);
        UserDto result = userService.saveUser(userDto);
        assertNotNull(result);
        assertEquals("shabeena", result.getName());
        assertEquals("shabee@gmail.com", result.getEmail());
    }
    @Test
    void getUserById_Test() {
        Long userId = 1L;
        User mockUser = new User(userId, "shabeena", "shabee@gmail.com", 1L, 1L);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        MentorDto mockMentorDto = new MentorDto(1L,1L,"mentor1", "Mentor1", "Expert1");
        CourseDto mockCourseDto = new CourseDto(1L,1L, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f);
        ResponseEntity<MentorDto> mentorResponseEntity = new ResponseEntity<>(mockMentorDto, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(MentorDto.class))).thenReturn(mentorResponseEntity);
        ResponseEntity<CourseDto> courseResponseEntity = new ResponseEntity<>(mockCourseDto, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(CourseDto.class))).thenReturn(courseResponseEntity);
        APIResponseDto result = userService.getUserById(userId);
        assertNotNull(result);
        assertNotNull(result.getUser());
        assertNotNull(result.getMentor());
        assertNotNull(result.getCourse());
    }

    @Test
    void getAllUsers_Test() {
        List<User> mockUsers = Arrays.asList(
                new User(1L, "shabeena", "shabee@gmail.com", 1L, 1L),
                new User(2L, "shabeena", "shabee@gmail.com", 2L, 2L)
        );
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserDto> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }
    @Test
    void updateUser_Test() {
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto(null, "Updated Name", "updated@gmail.com", 2L, 2L);
        User existingUser = new User(1L, "shabeena", "shabee@gmail.com", 1L, 1L);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any())).thenReturn(existingUser);
        UserDto result = userService.updateUser(userId, updatedUserDto);
        assertNotNull(result);
    }

    @Test
    void deleteUser_Test() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        userService.deleteUser(userId);
        verify(userRepository).deleteById(userId);
    }
}
