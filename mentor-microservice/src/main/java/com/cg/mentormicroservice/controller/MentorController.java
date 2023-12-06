package com.cg.mentormicroservice.controller;

import com.cg.mentormicroservice.dto.MentorDto;
import com.cg.mentormicroservice.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins ="http://localhost:3000")
@RequestMapping("api/mentors")
@AllArgsConstructor
@Tag(
        name = "Mentor_Controller",
        description = "Mentor_controller Exposes REST API"
)
public class MentorController {
    private MentorService mentorService;
    @Operation(
            summary = "Create Mentor REST API",
            description = "Create Mentor REST API is used to save mentor details in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // build create Mentor REST API
    @PostMapping("create")
    public ResponseEntity<MentorDto> createMentor(@Valid @RequestBody MentorDto mentor) {
        MentorDto savedMentor = mentorService.saveMentor(mentor);
        return new ResponseEntity<>(savedMentor, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get all Mentors REST API",
            description = "Get is used to get all Mentors"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
    @GetMapping
    public ResponseEntity<List<MentorDto>> getAllMentors() {
        List<MentorDto> mentors = mentorService.getAllMentors();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }
    @Operation(
            summary = "Get Mentor REST API",
            description = "Get is used to get Mentor"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
    // Build Get Mentor by Code REST API
    @GetMapping("{id}")
    public ResponseEntity<MentorDto> getMentor(@PathVariable("id") Long mentorId){
        MentorDto mentorDto = mentorService.getMentorById(mentorId);
        return ResponseEntity.ok(mentorDto);
    }
    @Operation(
            summary = "Update Mentor REST API",
            description = "Update Mentor REST API is used to update a particular mentor details in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Update Mentor REST API
    @PutMapping("/{mentorId}")
    public ResponseEntity<MentorDto> updateMentor(@PathVariable Long mentorId, @RequestBody MentorDto mentorDto) {
        MentorDto updatedMentor = mentorService.updateMentor(mentorId, mentorDto);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }
    @Operation(
            summary = "Delete Mentor REST API",
            description = "Delete Mentor REST API is used to delete a particular mentor from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Delete Mentor REST API
    @DeleteMapping("/{mentorId}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long mentorId) {
        mentorService.deleteMentor(mentorId);
        return new ResponseEntity<>("Mentor successfully deleted!", HttpStatus.OK);
    }
}
