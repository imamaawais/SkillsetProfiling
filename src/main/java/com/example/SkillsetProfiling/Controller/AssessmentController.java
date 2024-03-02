package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Assessment_DTO;
import com.example.SkillsetProfiling.Exception.AssessmentNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateAssessmentException;
import com.example.SkillsetProfiling.Service.Implementation.Assessment_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/assessment")
public class AssessmentController {

    private Assessment_Service service;

    @PostMapping("/add")
    public ResponseEntity<Assessment_DTO> addAssessment(@RequestBody Assessment_DTO assessmentDTO) {
        try {
            Assessment_DTO savedAssessment = service.addAssessment(assessmentDTO);
            return new ResponseEntity<>(savedAssessment, HttpStatus.CREATED);
        } catch (DuplicateAssessmentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Assessment_DTO>> getAllAssessments() {
        List<Assessment_DTO> assessments = service.getAllAssessments();

        if (assessments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @GetMapping("/getById/{assessmentId}")
    public ResponseEntity<Assessment_DTO> getAssessmentById(@PathVariable Integer assessmentId) {
        try {
            Assessment_DTO assessmentDTO = service.getAssessmentById(assessmentId);
            return new ResponseEntity<>(assessmentDTO, HttpStatus.OK);
        } catch (AssessmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{assessmentId}")
    public ResponseEntity<Assessment_DTO> updateAssessment(@PathVariable Integer assessmentId, @RequestBody Assessment_DTO updatedAssessmentDTO) {
        try {
            Assessment_DTO updatedAssessment = service.updateAssessment(assessmentId, updatedAssessmentDTO);
            return new ResponseEntity<>(updatedAssessment, HttpStatus.OK);
        } catch (AssessmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{assessmentId}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Integer assessmentId) {
        try {
            boolean deleted = service.deleteAssessment(assessmentId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (AssessmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
