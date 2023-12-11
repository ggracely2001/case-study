package com.cg.coursemicroservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.cg.coursemicroservice.dto.CourseDto;
import com.cg.coursemicroservice.entity.Course;
import com.cg.coursemicroservice.repository.CourseRepository;
import com.cg.coursemicroservice.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;
    @Test
    void saveCourse_Test() {
        CourseDto courseDto = new CourseDto(1L,1L, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f,LocalDateTime.now(),1f,50L);
        Course mockCourse = new Course(1L,1L, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f,LocalDateTime.now(),1f,50L);
        when(courseRepository.save(any())).thenReturn(mockCourse);
        CourseDto result = courseService.saveCourse(courseDto);
        assertNotNull(result);
    }
    @Test
    void getCourseById_Test() {
        Long courseId = 1L;
        Course mockCourse = new Course(1L,courseId, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f, LocalDateTime.now(),1f,50L);
        when(courseRepository.findByCourseId(courseId)).thenReturn(mockCourse);
        CourseDto result = courseService.getCourseById(courseId);
        assertNotNull(result);
        assertEquals(courseId, result.getCourseId());
    }
    @Test
    void getAllCourses_Test() {
        // Arrange
        List<Course> mockCourses = Arrays.asList(
                new Course(1L,1L, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f,LocalDateTime.now(),1f,50L),
                new Course(2L,2L, "Course2", "CS102", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor2", 60f,LocalDateTime.now(),1f,50L)
        );
        when(courseRepository.findAll()).thenReturn(mockCourses);
        List<CourseDto> result = courseService.getAllCourses();
        assertEquals(2, result.size());
    }
    @Test
    void updateCourse_Test() {
        Long courseId = 1L;
        CourseDto updatedCourseDto = new CourseDto(1L,1L, "UpdatedCourse", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "UpdatedMentor", 75f,LocalDateTime.now(),1f,50L);
        Course existingCourse = new Course(1L,1L, "Course1", "CS101", LocalDate.now(), LocalDate.now().plusDays(30), "Mentor1", 50f,LocalDateTime.now(),1f,50L);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(any())).thenReturn(existingCourse);
        CourseDto result = courseService.updateCourse(courseId, updatedCourseDto);
        assertNotNull(result);
        assertEquals(updatedCourseDto.getCourseName(), result.getCourseName());
        assertEquals(updatedCourseDto.getTotalHours(), result.getTotalHours());
    }

    @Test
    void deleteCourse_Test() {
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(true);
        courseService.deleteCourse(courseId);
        verify(courseRepository, times(1)).deleteById(courseId);
    }
}
