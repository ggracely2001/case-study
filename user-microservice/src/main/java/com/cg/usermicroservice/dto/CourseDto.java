package com.cg.usermicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Course_Dto Model info"
)
public class CourseDto {

    private Long courseId;
    @Schema(
            description = "Course Name"
    )
    private String courseName;
    @Schema(
            description = "Course Code"
    )
    private String courseCode;
    @Schema(
            description = "Course Start Date"
    )
    private LocalDate startDate;
    @Schema(
            description = "Course End Date"
    )
    private LocalDate endDate;
    @Schema(
            description = "Mentor Name"
    )
    private String assignedMentor;
    @Schema(
            description = "Total Duration"
    )
    private float totalHours;
}
