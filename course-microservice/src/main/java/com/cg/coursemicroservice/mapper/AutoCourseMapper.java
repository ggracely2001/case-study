package com.cg.coursemicroservice.mapper;

import com.cg.coursemicroservice.dto.CourseDto;
import com.cg.coursemicroservice.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoCourseMapper {

    AutoCourseMapper MAPPER = Mappers.getMapper(AutoCourseMapper.class);

    CourseDto mapToCourseDto(Course course);

    Course mapToCourse(CourseDto courseDto);
}

