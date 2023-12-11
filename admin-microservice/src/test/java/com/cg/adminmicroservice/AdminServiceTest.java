package com.cg.adminmicroservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.cg.adminmicroservice.dto.MentorDto;
import com.cg.adminmicroservice.dto.CourseDto;
import com.cg.adminmicroservice.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private AdminService adminService;
    @Test
    void getAllMentors_Test() {
        MentorDto[] mockMentors = { new MentorDto(1L,1L,"mentor1", "expert1", "active"), new MentorDto(2L, 2L,"Mentor2", "Expert2","active") };
        when(restTemplate.getForObject(anyString(), eq(MentorDto[].class))).thenReturn(mockMentors);
        List<MentorDto> result = adminService.getAllMentors();
        assertEquals(2, result.size());
    }
    @Test
    void getMentorById_Test() {
        Long mentorId = 1L;
        MentorDto mockMentor = new MentorDto(1L,1L,"mentor1", "expert1", "active");
        when(restTemplate.getForObject(anyString(), eq(MentorDto.class))).thenReturn(mockMentor);
        MentorDto result = adminService.getMentorById(mentorId);
        assertNotNull(result);
        assertEquals("mentor1", result.getMentorName());
        assertEquals("expert1", result.getExpertise());
    }
    @Test
    void createMentor_Test() {
        MentorDto mentorDto = new MentorDto(1L,null, "NewMentor", "NewExpert","active");
        MentorDto mockCreatedMentor = new MentorDto(1L,1L, "NewMentor", "NewExpert","active");
        when(restTemplate.postForObject(anyString(), any(), eq(MentorDto.class))).thenReturn(mockCreatedMentor);
        MentorDto result = adminService.createMentor(mentorDto);
        assertNotNull(result);
        assertEquals("NewMentor", result.getMentorName());
        assertEquals("NewExpert", result.getExpertise());
    }
    @Test
    void updateMentor_Test() {
        Long mentorId = 1L;
        MentorDto updatedMentorDto = new MentorDto(1L,null, "UpdatedMentor", "UpdatedExpert","active");
        MentorDto mockUpdatedMentor = new MentorDto(1L,mentorId, "UpdatedMentor", "UpdatedExpert","active");
        when(restTemplate.getForObject(anyString(), eq(MentorDto.class))).thenReturn(mockUpdatedMentor);
        MentorDto result = adminService.updateMentor(mentorId, updatedMentorDto);
        assertNotNull(result);
        assertEquals("UpdatedMentor", result.getMentorName());
        assertEquals("UpdatedExpert", result.getExpertise());
    }
    @Test
    void deleteMentor_Test() {
        Long mentorId = 1L;
        assertDoesNotThrow(() -> adminService.deleteMentor(mentorId));
        verify(restTemplate, times(1)).delete(anyString());
    }
    @Test
    void getAllCourses_Test() {
        CourseDto[] mockCourses = { new CourseDto(1L,1L, "Course1", "CS101", null, null, null, 50f),
                new CourseDto(2L,2L, "Course2", "CS102", null, null, null, 60f) };
        when(restTemplate.getForObject(anyString(), eq(CourseDto[].class))).thenReturn(mockCourses);
        List<CourseDto> result = adminService.getAllCourses();
        assertEquals(2, result.size());
    }
    @Test
    void getCourseById_Test() {
        Long courseId = 1L;
        CourseDto mockCourse = new CourseDto(1L,courseId, "Course1", "CS101", null, null, null, 50f);
        when(restTemplate.getForObject(anyString(), eq(CourseDto.class))).thenReturn(mockCourse);
        CourseDto result = adminService.getCourseById(courseId);
        assertNotNull(result);
        assertEquals("Course1", result.getCourseName());
        assertEquals("CS101", result.getCourseCode());
    }
    @Test
    void createCourse_Test() {
        CourseDto courseDto = new CourseDto(1L,null, "NewCourse", "CS102", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f);
        CourseDto mockCreatedCourse = new CourseDto(1L,1L, "NewCourse", "CS102", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f);
        when(restTemplate.postForObject(anyString(), any(), eq(CourseDto.class))).thenReturn(mockCreatedCourse);
        CourseDto result = adminService.createCourse(courseDto);
        assertNotNull(result);
        assertEquals("NewCourse", result.getCourseName());
        assertEquals("CS102", result.getCourseCode());
    }
    @Test
    void updateCourse_Test() {
        Long courseId = 1L;
        CourseDto updatedCourseDto = new CourseDto(1L,null, "UpdatedCourse", "CS103", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor2", 60f);
        CourseDto mockUpdatedCourse = new CourseDto(1L,courseId, "UpdatedCourse", "CS103", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor2", 60f);
        when(restTemplate.getForObject(anyString(), eq(CourseDto.class))).thenReturn(mockUpdatedCourse);
        CourseDto result = adminService.updateCourse(courseId, updatedCourseDto);
        assertNotNull(result);
        assertEquals("UpdatedCourse", result.getCourseName());
        assertEquals("CS103", result.getCourseCode());
    }
    @Test
    void deleteCourse_Test() {
        Long courseId = 1L;
        assertDoesNotThrow(() -> adminService.deleteCourse(courseId));
        verify(restTemplate, times(1)).delete(anyString());
    }
}
