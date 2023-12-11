package com.cg.mentormicroservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cg.mentormicroservice.dto.MentorDto;
import com.cg.mentormicroservice.entity.Mentor;
import com.cg.mentormicroservice.repository.MentorRepository;
import com.cg.mentormicroservice.service.serviceimpl.MentorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MentorServiceImplTest {

    @Mock
    private MentorRepository mentorRepository;

    @InjectMocks
    private MentorServiceImpl mentorService;

    @Test
    void getAllMentors_Test() {
        List<Mentor> mockMentors = Arrays.asList(
                new Mentor(1L,1L, "Mentor1", "Java", "Active"),
                new Mentor(2L,2L, "Mentor2", "Python", "Inactive")
        );
        when(mentorRepository.findAll()).thenReturn(mockMentors);
        List<MentorDto> result = mentorService.getAllMentors();
        assertEquals(2, result.size());

    }

    @Test
    void getMentorById_Test() {
        Long mentorId = 1L;
        Mentor mockMentor = new Mentor(1L,1L, "Mentor1", "Java", "Active");
        when(mentorRepository.findByMentorId(mentorId)).thenReturn(mockMentor);
        MentorDto result = mentorService.getMentorById(mentorId);
        assertNotNull(result);

    }

    @Test
    void saveMentor_Test() {
        MentorDto mentorDto = new MentorDto(1L,1L, "Mentor1", "Java", "Active");
        Mentor mockMentor = new Mentor(1L,1L, "Mentor1", "Java", "Active");
        when(mentorRepository.save(any())).thenReturn(mockMentor);
        MentorDto result = mentorService.saveMentor(mentorDto);
        assertNotNull(result);

    }

    @Test
    void updateMentor_Test() {
        Long mentorId = 1L;
        MentorDto updatedMentorDto = new MentorDto(1L,1L, "UpdatedMentor", "UpdatedExpertise", "UpdatedStatus");

        Mentor existingMentor = new Mentor(1L,mentorId, "Mentor1", "Java", "Active");
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(existingMentor));
        when(mentorRepository.save(any())).thenReturn(existingMentor);

        MentorDto result = mentorService.updateMentor(mentorId, updatedMentorDto);

        assertNotNull(result);
        assertEquals(updatedMentorDto.getMentorName(), result.getMentorName());
        assertEquals(updatedMentorDto.getExpertise(), result.getExpertise());
        assertEquals(updatedMentorDto.getStatus(), result.getStatus());
    }

    @Test
    void deleteMentor_Test() {
        Long mentorId = 1L;
        when(mentorRepository.existsById(mentorId)).thenReturn(true);
        mentorService.deleteMentor(mentorId);
        verify(mentorRepository, times(1)).deleteById(mentorId);
    }


}