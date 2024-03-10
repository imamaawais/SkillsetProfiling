package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Assessment_Scores_DTO;
import com.example.SkillsetProfiling.Exception.AssessmentScoreNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateAssessmentScoreException;
import com.example.SkillsetProfiling.Service.Implementation.Assessment_Scores_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/assessment_scores")
public class AssessmentScoresController {

    private Assessment_Scores_Service service;

    @PostMapping("/add")
    public ResponseEntity<Assessment_Scores_DTO> addAssessmentScore(@RequestBody Assessment_Scores_DTO assessmentScoresDTO) {
        try {
            Assessment_Scores_DTO savedAssessmentScores = service.addAssessmentScore(assessmentScoresDTO);
            return new ResponseEntity<>(savedAssessmentScores, HttpStatus.CREATED);
        } catch (DuplicateAssessmentScoreException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Assessment_Scores_DTO>> getAllAssessmentScores() {
        List<Assessment_Scores_DTO> assessmentScores = service.getAllAssessmentScores();

        if (assessmentScores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(assessmentScores, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{assessmentId}/{questionId}")
    public ResponseEntity<Assessment_Scores_DTO> getAssessmentScoreByIds(@PathVariable Integer assessmentId, @PathVariable Integer questionId) {
        try {
            Assessment_Scores_DTO assessmentScoresDTO = service.getAssessmentScoreByIds(assessmentId, questionId);
            return new ResponseEntity<>(assessmentScoresDTO, HttpStatus.OK);
        } catch (AssessmentScoreNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{assessmentId}/{questionId}")
    public ResponseEntity<Assessment_Scores_DTO> updateAssessmentScore(@PathVariable Integer assessmentId, @PathVariable Integer questionId, @RequestBody Assessment_Scores_DTO updatedAssessmentScoresDTO) {
        try {
            Assessment_Scores_DTO updatedAssessmentScores = service.updateAssessmentScore(assessmentId, questionId, updatedAssessmentScoresDTO);
            return new ResponseEntity<>(updatedAssessmentScores, HttpStatus.OK);
        } catch (AssessmentScoreNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{assessmentId}/{questionId}")
    public ResponseEntity<Void> deleteAssessmentScore(@PathVariable Integer assessmentId, @PathVariable Integer questionId) {
        try {
            boolean deleted = service.deleteAssessmentScore(assessmentId, questionId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (AssessmentScoreNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
