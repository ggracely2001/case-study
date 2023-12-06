package com.cg.usermicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "User_Dto Model info"
)
public class UserDto {

    private Long userId;

    @Schema(
            description = "Name"
    )
    private String name;
    @Schema(
            description = "User Email"
    )
    private String email;
    @Schema(
            description = "Mentor Id"
    )
    private Long mentorId;
    @Schema(
            description = "Course Id"
    )
    private Long courseId;
}
