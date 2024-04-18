package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Question_Paper_Questions_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionPaperQuesitonException;
import com.example.SkillsetProfiling.Exception.QuestionPaperQuestionsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Question_Paper_Questions_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/question_paper_questions")
public class QuestionPaperQuestionsController {

    private Question_Paper_Questions_Service service;

    @PostMapping("/add")
    public ResponseEntity<Question_Paper_Questions_DTO> addQuestionPaperQuestions(@RequestBody Question_Paper_Questions_DTO questionPaperQuestionsDTO) {
        try {
            Question_Paper_Questions_DTO savedQuestionPaperQuestions = service.addQuestionPaperQuestions(questionPaperQuestionsDTO);
            return new ResponseEntity<>(savedQuestionPaperQuestions, HttpStatus.CREATED);
        } catch (DuplicateQuestionPaperQuesitonException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question_Paper_Questions_DTO>> getAllQuestionPaperQuestions() {
        List<Question_Paper_Questions_DTO> questionPaperQuestions = service.getAllQuestionPaperQuestions();

        if (questionPaperQuestions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionPaperQuestions, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{questionPaperId}/{questionId}")
    public ResponseEntity<Question_Paper_Questions_DTO> getQuestionPaperQuestionsByIds(@PathVariable Integer questionPaperId, @PathVariable Integer questionId) {
        try {
            Question_Paper_Questions_DTO questionPaperQuestionsDTO = service.getQuestionPaperQuestionsByIds(questionPaperId, questionId);
            return new ResponseEntity<>(questionPaperQuestionsDTO, HttpStatus.OK);
        } catch (QuestionPaperQuestionsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFromQuestionPaperId/{questionPaperId}")
    public ResponseEntity<List<Question_Paper_Questions_DTO>> getQuestionPaperQuestionsByQuestionPaperId(@PathVariable Integer questionPaperId) {
        List<Question_Paper_Questions_DTO> questionPaperQuestions = service.getQuestionPaperQuestionsByQuestionPaperId(questionPaperId);

        if (questionPaperQuestions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionPaperQuestions, HttpStatus.OK);
    }

    @PutMapping("/update/{questionPaperId}/{questionId}")
    public ResponseEntity<Question_Paper_Questions_DTO> updateQuestionPaperQuestions(@PathVariable Integer questionPaperId, @PathVariable Integer questionId, @RequestBody Question_Paper_Questions_DTO updatedQuestionPaperQuestionsDTO) {
        try {
            Question_Paper_Questions_DTO updatedQuestionPaperQuestions = service.updateQuestionPaperQuestions(questionPaperId, questionId, updatedQuestionPaperQuestionsDTO);
            return new ResponseEntity<>(updatedQuestionPaperQuestions, HttpStatus.OK);
        } catch (QuestionPaperQuestionsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{questionPaperId}/{questionId}")
    public ResponseEntity<Void> deleteQuestionPaperQuestions(@PathVariable Integer questionPaperId, @PathVariable Integer questionId) {
        try {
            boolean deleted = service.deleteQuestionPaperQuestions(questionPaperId, questionId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QuestionPaperQuestionsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
