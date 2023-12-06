package com.cg.coursemicroservice.service;

import com.cg.coursemicroservice.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto saveCourse(CourseDto courseDto);

    CourseDto getCourseById(Long courseId);

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(Long courseId , CourseDto courseDto);

    void deleteCourse(Long courseId);


}
