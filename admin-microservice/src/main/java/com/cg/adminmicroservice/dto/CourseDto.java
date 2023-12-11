package com.cg.adminmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseDto {
    private Long id;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String assignedMentor;
    private float totalHours;
}
