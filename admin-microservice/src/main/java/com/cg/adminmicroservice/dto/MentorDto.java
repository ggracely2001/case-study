package com.cg.adminmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MentorDto {
    private Long id;

    private Long mentorId;

    private String mentorName;

    private String expertise;

    private String status;
}
