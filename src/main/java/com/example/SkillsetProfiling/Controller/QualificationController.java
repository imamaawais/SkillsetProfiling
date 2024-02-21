package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Qualification_DTO;
import com.example.SkillsetProfiling.Exception.QualificationNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Qualification_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/qualifications")
public class QualificationController {
    private Qualification_Service service;

    @PostMapping
    public ResponseEntity<Qualification_DTO> addQualification(@RequestBody Qualification_DTO qualificationDTO) {
        Qualification_DTO addedQualification = service.addQualification(qualificationDTO);
        return new ResponseEntity<>(addedQualification, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<Qualification_DTO>> getAllQualifications() {
        List<Qualification_DTO> qualifications = service.getAllQualifications();

        if (qualifications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(qualifications, HttpStatus.OK);
    }

    @GetMapping("/id/{qualificationID}")
    public ResponseEntity<Qualification_DTO> getQualificationByID(@PathVariable Integer qualificationID) throws QualificationNotFoundException {
        Qualification_DTO qualificationDTO = service.getQualificationByID(qualificationID);
        return new ResponseEntity<>(qualificationDTO, HttpStatus.OK);
    }

    @GetMapping("/name/{qualificationName}")
    public ResponseEntity<Qualification_DTO> getQualificationByName(@PathVariable String qualificationName) {
        try {
            Qualification_DTO qualificationDTO = service.getQualificationByName(qualificationName);
            return new ResponseEntity<>(qualificationDTO, HttpStatus.OK);
        } catch (QualificationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{qualificationID}")
    public ResponseEntity<Qualification_DTO> updateQualification(@PathVariable Integer qualificationID, @RequestBody Qualification_DTO updatedQualificationDTO) {
        try {
            Qualification_DTO updatedQualification = service.updateQualification(qualificationID, updatedQualificationDTO);
            return new ResponseEntity<>(updatedQualification, HttpStatus.OK);
        } catch (QualificationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{qualificationID}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Integer qualificationID) {
        try {
            boolean deleted = service.deleteQualification(qualificationID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // Handle case where deletion was not successful
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QualificationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
