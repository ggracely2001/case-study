package com.cg.coursemicroservice.controller;

import com.cg.coursemicroservice.dto.CourseDto;
import com.cg.coursemicroservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/courses")
@AllArgsConstructor
@Tag(
        name = "Course_Controller",
        description = "Course_controller Exposes REST API"
)
public class CourseController {
    private CourseService courseService;

    @Operation(
            summary = "Create Course REST API",
            description = "Create Course REST API is used to save Course in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // build create Course REST API
    @PostMapping("create")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto course) {
        CourseDto savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Course REST API",
            description = "Get is used to get Course"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
// Build Get Course by Code REST API
    @GetMapping("{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("id") Long courseId) {
        CourseDto courseDto = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all Courses REST API",
            description = "Get is used to get all Courses"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Course REST API",
            description = "Update Course REST API is used to update a particular Course in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Update Course REST API
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(courseId, courseDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Course REST API",
            description = "Delete Course REST API is used to delete a particular Course from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Delete Course REST API
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Course successfully deleted!", HttpStatus.OK);
    }

}
