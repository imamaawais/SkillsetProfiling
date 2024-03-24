package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Mentor_Details_DTO;
import com.example.SkillsetProfiling.Exception.AdministratorDetailsNotFoundException;
import com.example.SkillsetProfiling.Exception.MentorDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/mentor_details")
public class MentorDetailsController {

    private IMentor_Details_Service service;

    @PostMapping("/add")
    public ResponseEntity<Mentor_Details_DTO> addMentorDetails(@RequestBody Mentor_Details_DTO mentorDetailsDTO) {
        Mentor_Details_DTO addedMentorDetails = service.addMentorDetails(mentorDetailsDTO);
        return new ResponseEntity<>(addedMentorDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{mentorDetailsID}")
    public ResponseEntity<Mentor_Details_DTO> getMentorDetailsByMentorID(@PathVariable Integer mentorDetailsID) {
        try {
            Mentor_Details_DTO mentorDetailsDTO = service.getMentorDetailsByMentorID(mentorDetailsID);
            return new ResponseEntity<>(mentorDetailsDTO, HttpStatus.OK);
        } catch (MentorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFromEmail/{email}")
    public ResponseEntity<Integer> getMentorIDByEmail(@PathVariable String email) {
        try {
            Integer id = service.getMentorIDByEmail(email);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (MentorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Mentor_Details_DTO>> getAllMentorDetails() {
        List<Mentor_Details_DTO> mentorDetailsList = service.getAllMentorDetails();

        if (mentorDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mentorDetailsList, HttpStatus.OK);
    }

    @PutMapping("/update/{mentorID}")
    public ResponseEntity<Mentor_Details_DTO> updateMentorDetails(@PathVariable Integer mentorID,
                                                                  @RequestBody Mentor_Details_DTO updatedMentorDetailsDTO) {
        try {
            Mentor_Details_DTO updatedMentorDetails = service.updateMentorDetails(mentorID, updatedMentorDetailsDTO);
            return new ResponseEntity<>(updatedMentorDetails, HttpStatus.OK);
        } catch (MentorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{mentorID}")
    public ResponseEntity<Void> deleteMentorDetails(@PathVariable Integer mentorID) {
        try {
            boolean deleted = service.deleteMentorDetails(mentorID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (MentorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
