package com.cg.mentormicroservice.service;

import com.cg.mentormicroservice.dto.MentorDto;
import com.cg.mentormicroservice.entity.Mentor;

import java.util.List;

public interface MentorService {
    List<MentorDto> getAllMentors();

    MentorDto getMentorById(Long mentorId);

    MentorDto saveMentor(MentorDto mentorDto);

    MentorDto updateMentor(Long projectId, MentorDto projectDto);

    void deleteMentor(Long mentorId);
}
