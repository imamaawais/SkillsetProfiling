package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Mentor_Specialization_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateMentorSpecializationException;
import com.example.SkillsetProfiling.Exception.MentorSpecializationNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Mentor_Specialization_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/mentor_specialization")
public class MentorSpecializationController {

    private Mentor_Specialization_Service mentorSpecializationService;

    @PostMapping("/add")
    public ResponseEntity<Mentor_Specialization_DTO> addMentorSpecialization(@RequestBody Mentor_Specialization_DTO mentorSpecializationDTO) {
        try {
            Mentor_Specialization_DTO savedMentorSpecialization = mentorSpecializationService.addMentorSpecialization(mentorSpecializationDTO);
            return new ResponseEntity<>(savedMentorSpecialization, HttpStatus.CREATED);
        } catch (DuplicateMentorSpecializationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Mentor_Specialization_DTO>> getAllMentorSpecializations() {
        List<Mentor_Specialization_DTO> mentorSpecializations = mentorSpecializationService.getAllMentorSpecializations();

        if (mentorSpecializations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mentorSpecializations, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{mentorId}/{skillId}")
    public ResponseEntity<Mentor_Specialization_DTO> getMentorSpecializationByIds(@PathVariable Integer mentorId, @PathVariable Integer skillId) {
        try {
            Mentor_Specialization_DTO mentorSpecializationDTO = mentorSpecializationService.getMentorSpecializationByIds(mentorId, skillId);
            return new ResponseEntity<>(mentorSpecializationDTO, HttpStatus.OK);
        } catch (MentorSpecializationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{mentorId}/{skillId}")
    public ResponseEntity<Mentor_Specialization_DTO> updateMentorSpecialization(@PathVariable Integer mentorId, @PathVariable Integer skillId, @RequestBody Mentor_Specialization_DTO updatedMentorSpecializationDTO) {
        try {
            Mentor_Specialization_DTO updatedMentorSpecialization = mentorSpecializationService.updateMentorSpecialization(mentorId, skillId, updatedMentorSpecializationDTO);
            return new ResponseEntity<>(updatedMentorSpecialization, HttpStatus.OK);
        } catch (MentorSpecializationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{mentorId}/{skillId}")
    public ResponseEntity<Void> deleteMentorSpecialization(@PathVariable Integer mentorId, @PathVariable Integer skillId) {
        try {
            boolean deleted = mentorSpecializationService.deleteMentorSpecialization(mentorId, skillId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (MentorSpecializationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
