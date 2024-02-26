package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Mentor_Qualification_DTO;
import com.example.SkillsetProfiling.Exception.MentorDetailsNotFoundException;
import com.example.SkillsetProfiling.Key.Mentor_Qualification_Key;
import com.example.SkillsetProfiling.Service.Implementation.Mentor_Qualification_Service;
import com.example.SkillsetProfiling.Service.Interface.IMentor_Qualification_Service;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/mentor_qualifications")
public class
MentorQualificationController {

    private final IMentor_Qualification_Service service;
    private static final Logger logger = LoggerFactory.getLogger(Mentor_Qualification_Service.class);

    @PostMapping("/add")
    public ResponseEntity<Mentor_Qualification_DTO> addMentorQualification(@RequestBody Mentor_Qualification_DTO mentorQualificationDTO) {
        Mentor_Qualification_DTO savedDTO = service.addMentorQualification(mentorQualificationDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Mentor_Qualification_DTO>> getAllMentorQualifications() {
        List<Mentor_Qualification_DTO> mentorQualificationList = service.getAllMentorQualifications();

        if ( mentorQualificationList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mentorQualificationList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{mentorID}/{qualificationID}/{domainID}")
    public ResponseEntity<Mentor_Qualification_DTO> getMentorQualificationById(
            @PathVariable Integer mentorID,
            @PathVariable Integer qualificationID,
            @PathVariable Integer domainID) {

        Mentor_Qualification_Key key = new Mentor_Qualification_Key(mentorID, qualificationID, domainID);
        try{
            Mentor_Qualification_DTO mentorQualificationDTO = service.getMentorQualificationById(key);
            return new ResponseEntity<>(mentorQualificationDTO, HttpStatus.OK);
        } catch (MentorDetailsNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{mentorID}/{qualificationID}/{domainID}")
    public ResponseEntity<Mentor_Qualification_DTO> updateMentorQualification(
            @PathVariable Integer mentorID,
            @PathVariable Integer qualificationID,
            @PathVariable Integer domainID,
            @RequestBody Mentor_Qualification_DTO updatedMentorQualificationDTO) {

        Mentor_Qualification_Key key = new Mentor_Qualification_Key(mentorID, qualificationID, domainID);
        try{
            Mentor_Qualification_DTO updatedMentorQualification = service.updateMentorQualification(key, updatedMentorQualificationDTO);
            return new ResponseEntity<>(updatedMentorQualification, HttpStatus.OK);
        } catch (MentorDetailsNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/delete/{mentorID}/{qualificationID}/{domainID}")
    public ResponseEntity<Void> deleteMentorQualification(
            @PathVariable Integer mentorID,
            @PathVariable Integer qualificationID,
            @PathVariable Integer domainID) {
        Mentor_Qualification_Key key = new Mentor_Qualification_Key(mentorID, qualificationID, domainID);
        try{
            logger.info("received mentor qualification with key: {}", key);
            boolean deleted = service.deleteMentorQualification(key);

            if (deleted){
                return new ResponseEntity<>(HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (MentorDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
