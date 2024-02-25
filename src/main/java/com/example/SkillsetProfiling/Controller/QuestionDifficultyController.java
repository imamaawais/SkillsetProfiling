package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Question_Difficulty_DTO;
import com.example.SkillsetProfiling.Exception.QuestionDifficultyNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Question_Difficulty_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/question_difficulty")
public class QuestionDifficultyController {
    private Question_Difficulty_Service service;


    @PostMapping("/add")
    public ResponseEntity<Question_Difficulty_DTO> addQuestionDifficulty(@RequestBody Question_Difficulty_DTO questionDifficultyDTO) {
        Question_Difficulty_DTO addedQuestionDifficulty = service.addQuestionDifficulty(questionDifficultyDTO);
        return new ResponseEntity<>(addedQuestionDifficulty, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{difficultyID}")
    public ResponseEntity<Question_Difficulty_DTO> getQuestionDifficultyByDifficultyID(@PathVariable Integer difficultyID) {
        try {
            Question_Difficulty_DTO questionDifficultyDTO = service.getQuestionDifficultyByDifficultyID(difficultyID);
            return new ResponseEntity<>(questionDifficultyDTO, HttpStatus.OK);
        } catch (QuestionDifficultyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question_Difficulty_DTO>> getAllQuestionDifficulties() {
        List<Question_Difficulty_DTO> questionDifficultyList = service.getAllQuestionDifficulties();

        if (questionDifficultyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionDifficultyList, HttpStatus.OK);
    }

    @PutMapping("/update/{difficultyID}")
    public ResponseEntity<Question_Difficulty_DTO> updateQuestionDifficulty(@PathVariable Integer difficultyID,
                                                                            @RequestBody Question_Difficulty_DTO updatedQuestionDifficultyDTO) {
        try {
            Question_Difficulty_DTO updatedQuestionDifficulty = service.updateQuestionDifficulty(difficultyID, updatedQuestionDifficultyDTO);
            return new ResponseEntity<>(updatedQuestionDifficulty, HttpStatus.OK);
        } catch (QuestionDifficultyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{difficultyID}")
    public ResponseEntity<Void> deleteQuestionDifficulty(@PathVariable Integer difficultyID) {
        try {
            boolean deleted = service.deleteQuestionDifficulty(difficultyID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QuestionDifficultyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
