package com.cg.adminmicroservice.service;

import com.cg.adminmicroservice.dto.CourseDto;
import com.cg.adminmicroservice.dto.MentorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;

@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    private final String MENTOR_API_URL = "http://localhost:8080/api/mentors";
    private final String COURSE_API_URL = "http://localhost:8081/api/courses";


    public List<MentorDto> getAllMentors() {
        return List.of(restTemplate.getForObject(MENTOR_API_URL, MentorDto[].class));
    }

    public MentorDto getMentorById(Long mentorId) {
        return restTemplate.getForObject(MENTOR_API_URL + "/" + mentorId, MentorDto.class);
    }

    public MentorDto createMentor(MentorDto mentorDto) {
        return restTemplate.postForObject(MENTOR_API_URL + "/create", mentorDto, MentorDto.class);
    }

    public MentorDto updateMentor(Long mentorId, MentorDto mentorDto) {
        String url = MENTOR_API_URL + "/" + mentorId;
        restTemplate.put(url, mentorDto);
        return restTemplate.getForObject(url, MentorDto.class);
    }

    public void deleteMentor(Long mentorId) {
        restTemplate.delete(MENTOR_API_URL + "/" + mentorId);
    }

    //CRUD operations for Course

    public List<CourseDto> getAllCourses() {
        return List.of(restTemplate.getForObject(COURSE_API_URL, CourseDto[].class));
    }

    public CourseDto getCourseById(Long courseId) {
        return restTemplate.getForObject(COURSE_API_URL + "/" + courseId, CourseDto.class);
    }


    public CourseDto createCourse(CourseDto courseDto) {
        return restTemplate.postForObject(COURSE_API_URL + "/create", courseDto, CourseDto.class);
    }

    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        String url = COURSE_API_URL + "/" + courseId;
        restTemplate.put(url, courseDto);
        return restTemplate.getForObject(url, CourseDto.class);
    }

    public void deleteCourse(Long courseId) {

        restTemplate.delete(COURSE_API_URL + "/" + courseId);
    }

}
