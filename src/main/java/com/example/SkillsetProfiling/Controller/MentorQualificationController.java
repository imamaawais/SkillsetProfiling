package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import com.example.SkillsetProfiling.Service.Implementation.Mentor_Qualification_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/mentor_qualifications")
public class MentorQualificationController {

    private final Mentor_Qualification_Service service;

    @GetMapping
    public List<Mentor_Qualification_DTO> getAllMentorQualifications() {
        return service.getAllMentorQualifications();
    }

    @GetMapping("/{mentorID}/{qualificationID}/{domainID}")
    public ResponseEntity<Mentor_Qualification_DTO> getMentorQualificationById(
            @PathVariable Integer mentorID,
            @PathVariable Integer qualificationID,
            @PathVariable Integer domainID) {

        Mentor_Qualification_Key key = new Mentor_Qualification_Key(mentorID, qualificationID, domainID);
        Optional<Mentor_Qualification_DTO> mentorQualification = service.getMentorQualificationById(key);

        return mentorQualification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mentor_Qualification_DTO> saveMentorQualification(@RequestBody Mentor_Qualification_DTO mentorQualificationDTO) {
        Mentor_Qualification_DTO savedDTO = service.saveMentorQualification(mentorQualificationDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{mentorID}/{qualificationID}/{domainID}")
    public ResponseEntity<Void> deleteMentorQualification(
            @PathVariable Integer mentorID,
            @PathVariable Integer qualificationID,
            @PathVariable Integer domainID) {

        Mentor_Qualification_Key key = new Mentor_Qualification_Key(mentorID, qualificationID, domainID);
        service.deleteMentorQualification(key);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
