package com.cg.mentormicroservice.service.serviceimpl;

import com.cg.mentormicroservice.dto.MentorDto;
import com.cg.mentormicroservice.entity.Mentor;
import com.cg.mentormicroservice.mapper.AutoMentorMapper;
import com.cg.mentormicroservice.repository.MentorRepository;
import com.cg.mentormicroservice.service.MentorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {
    private MentorRepository mentorRepository;

    @Override
    public List<MentorDto> getAllMentors() {
        List<Mentor> mentors = mentorRepository.findAll();
        return mentors.stream().map((mentor) -> AutoMentorMapper.MAPPER.mapToMentorDto(mentor))
                .collect(Collectors.toList());
    }
    @Override
    public MentorDto getMentorById(Long mentorId) {
        Mentor mentor = mentorRepository.findByMentorId(mentorId);
        return AutoMentorMapper.MAPPER.mapToMentorDto(mentor);
    }

    @Override
    public MentorDto saveMentor(MentorDto mentorDto) {
        //convert mentor dto to mentor jpa entity
        Mentor mentor = AutoMentorMapper.MAPPER.mapToMentor(mentorDto);

        Mentor savedMentor = mentorRepository.save(mentor);

        MentorDto savedMentorDto = AutoMentorMapper.MAPPER.mapToMentorDto(savedMentor);

        return savedMentorDto;
    }
    @Override
    public MentorDto updateMentor(Long mentorId, MentorDto mentorDto) {
        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);
        if (optionalMentor.isPresent()) {
            Mentor existingMentor = optionalMentor.get();
            // Update the fields as needed
            existingMentor.setMentorName(mentorDto.getMentorName());
            existingMentor.setExpertise(mentorDto.getExpertise());
            existingMentor.setStatus(mentorDto.getStatus());
            Mentor updatedMentor = mentorRepository.save(existingMentor);
            return AutoMentorMapper.MAPPER.mapToMentorDto(updatedMentor);
        } else {
            throw new EntityNotFoundException("Mentor with ID " + mentorId + " not found");
        }
    }
    @Override
    public void deleteMentor(Long mentorId) {
        if (mentorRepository.existsById(mentorId)) {
            mentorRepository.deleteById(mentorId);
        } else {
            throw new EntityNotFoundException("Mentor with ID " + mentorId + " not found");
        }
    }
}
