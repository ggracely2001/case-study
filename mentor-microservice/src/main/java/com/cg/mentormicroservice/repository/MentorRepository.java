package com.cg.mentormicroservice.repository;

import com.cg.mentormicroservice.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor findByMentorId(Long mentorId);
}
