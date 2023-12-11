package com.cg.mentormicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Mentor_Dto Model info"
)
public class MentorDto {

    private Long id;
    @Schema(
            description = "Mentor Id"
    )
    private Long mentorId;
    @Schema(
            description = "Mentor Name"
    )
    private String mentorName;
    @Schema(
            description = "Expertise"
    )
    private String expertise;
    @Schema(
            description = "Availability status"
    )
    private String status;
}
