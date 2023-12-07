package com.cg.adminmicroservice.controller;

import com.cg.adminmicroservice.dto.CourseDto;
import com.cg.adminmicroservice.dto.MentorDto;
import com.cg.adminmicroservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin (origins ="http://localhost:4200")
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }

    @GetMapping("/mentors")
    public List<MentorDto> getAllMentors() {

        return adminService.getAllMentors();
    }

    @GetMapping("/mentors/{mentorId}")
    public ResponseEntity<MentorDto> getMentorById(@PathVariable Long mentorId) {
        MentorDto mentorDto = adminService.getMentorById(mentorId);
        return ResponseEntity.ok(mentorDto);
    }

    @PostMapping("/mentors/create")
    public ResponseEntity<MentorDto> createMentor(@Valid @RequestBody MentorDto mentorDto) {
        MentorDto createdMentor = adminService.createMentor(mentorDto);
        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
    }

    @PutMapping("/mentors/{mentorId}")
    public ResponseEntity<MentorDto> updateMentor(@PathVariable Long mentorId, @RequestBody MentorDto mentorDto) {
        MentorDto updatedMentor = adminService.updateMentor(mentorId, mentorDto);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }

    @DeleteMapping("/mentors/{mentorId}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long mentorId) {
        adminService.deleteMentor(mentorId);
        return new ResponseEntity<>("Mentor successfully deleted!", HttpStatus.OK);
    }

       //CRUD operations for Course

    @GetMapping("/courses")
    public List<CourseDto> getAllCourses() {
        return adminService.getAllCourses();
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId) {
        CourseDto courseDto = adminService.getCourseById(courseId);
        return ResponseEntity.ok(courseDto);
    }

    @PostMapping("/courses/create")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) {
        CourseDto createdCourse = adminService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = adminService.updateCourse(courseId, courseDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        adminService.deleteCourse(courseId);
        return new ResponseEntity<>("Course successfully deleted!", HttpStatus.OK);
    }

    }

