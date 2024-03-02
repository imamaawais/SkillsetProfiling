package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Question_Paper_DTO;
import com.example.SkillsetProfiling.Exception.QuestionPaperNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Question_Paper_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/question_paper")
public class QuestionPaperController {

    private final Question_Paper_Service service;

    @PostMapping("/add")
    public ResponseEntity<Question_Paper_DTO> addQuestionPaper(@RequestBody Question_Paper_DTO questionPaperDTO) {
        Question_Paper_DTO savedQuestionPaper = service.addQuestionPaper(questionPaperDTO);
        return new ResponseEntity<>(savedQuestionPaper, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question_Paper_DTO>> getAllQuestionPapers() {
        List<Question_Paper_DTO> questionPapers = service.getAllQuestionPapers();

        if (questionPapers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionPapers, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{QuestionPaperID}")
    public ResponseEntity<Question_Paper_DTO> getQuestionPaperById(@PathVariable int QuestionPaperID) {
        try {
            Question_Paper_DTO questionPaperDTO = service.getQuestionPaperById(QuestionPaperID);
            return new ResponseEntity<>(questionPaperDTO, HttpStatus.OK);
        } catch (QuestionPaperNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{QuestionPaperID}")
    public ResponseEntity<Question_Paper_DTO> updateQuestionPaper(@PathVariable Integer QuestionPaperID, @RequestBody Question_Paper_DTO updatedQuestionPaperDTO) {
        try {
            Question_Paper_DTO updatedQuestionPaper = service.updateQuestionPaper(QuestionPaperID, updatedQuestionPaperDTO);
            return new ResponseEntity<>(updatedQuestionPaper, HttpStatus.OK);
        } catch (QuestionPaperNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{QuestionPaperID}")
    public ResponseEntity<Void> deleteQuestionPaper(@PathVariable Integer QuestionPaperID) {
        try {
            boolean deleted = service.deleteQuestionPaper(QuestionPaperID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QuestionPaperNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
