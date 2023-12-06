package com.cg.mentormicroservice.mapper;

import com.cg.mentormicroservice.dto.MentorDto;
import com.cg.mentormicroservice.entity.Mentor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMentorMapper {

    AutoMentorMapper MAPPER = Mappers.getMapper(AutoMentorMapper.class);

    MentorDto mapToMentorDto(Mentor mentor);

    Mentor mapToMentor(MentorDto mentorDto);
}

