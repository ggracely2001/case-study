package com.cg.usermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    private UserDto user;
    private MentorDto mentor;
    private CourseDto course;
//    private SessionDto session;
}
