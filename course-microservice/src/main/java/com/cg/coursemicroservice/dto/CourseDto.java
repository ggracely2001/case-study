package com.cg.coursemicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    //session
    @Schema(
            description = "Session Date and Time"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sessionDateTime;
    @Schema(
            description = "Session Duration"
    )
    private float sessionDuration;
    @Schema(
            description = "Total Participants"
    )
    private Long totalParticipants;
}
