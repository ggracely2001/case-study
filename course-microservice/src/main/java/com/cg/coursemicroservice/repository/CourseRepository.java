package com.cg.coursemicroservice.repository;

import com.cg.coursemicroservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{

    Course findByCourseId(Long courseId);
}
