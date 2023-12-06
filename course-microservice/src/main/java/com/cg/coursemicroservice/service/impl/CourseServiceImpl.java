package com.cg.coursemicroservice.service.impl;

import com.cg.coursemicroservice.dto.CourseDto;
import com.cg.coursemicroservice.entity.Course;
import com.cg.coursemicroservice.mapper.AutoCourseMapper;
import com.cg.coursemicroservice.repository.CourseRepository;
import com.cg.coursemicroservice.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        Course course = AutoCourseMapper.MAPPER.mapToCourse(courseDto);
        Course savedCourse = courseRepository.save(course);
        CourseDto savedCourseDto = AutoCourseMapper.MAPPER.mapToCourseDto(savedCourse);
        return savedCourseDto;
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Course course = courseRepository.findByCourseId(courseId);
        return AutoCourseMapper.MAPPER.mapToCourseDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> AutoCourseMapper.MAPPER.mapToCourseDto(course))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto updatedCourseDto) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();

            // Step 2: Update the properties of the existing Course entity
            existingCourse.setCourseName(updatedCourseDto.getCourseName());
            existingCourse.setCourseCode(updatedCourseDto.getCourseCode());
            existingCourse.setStartDate(updatedCourseDto.getStartDate());
            existingCourse.setEndDate(updatedCourseDto.getEndDate());
            existingCourse.setAssignedMentor(updatedCourseDto.getAssignedMentor());
            existingCourse.setTotalHours(updatedCourseDto.getTotalHours());
            existingCourse.setSessionDateTime(updatedCourseDto.getSessionDateTime());
            existingCourse.setSessionDuration(updatedCourseDto.getSessionDuration());
            existingCourse.setTotalParticipants(updatedCourseDto.getTotalParticipants());
            // Update other properties as needed

            // Step 3: Save the updated Course entity back to the repository
            Course updatedCourse = courseRepository.save(existingCourse);

            // Step 4: Return the updated CourseDto
            return AutoCourseMapper.MAPPER.mapToCourseDto(updatedCourse);
        } else {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    @Override
    public void deleteCourse(Long courseId) {

        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
        } else {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found");
        }

    }

}
