package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Question_Bank_DTO;
import com.example.SkillsetProfiling.Exception.QuestionBankNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Question_Bank_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/question_bank")
public class QuestionBankController {
    private Question_Bank_Service service;


    @PostMapping("/add")
    public ResponseEntity<Question_Bank_DTO> addQuestionBank(@RequestBody Question_Bank_DTO questionBankDTO) {
        Question_Bank_DTO addedQuestionBank = service.addQuestionBank(questionBankDTO);
        return new ResponseEntity<>(addedQuestionBank, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{questionID}")
    public ResponseEntity<Question_Bank_DTO> getQuestionBankByQuestionID(@PathVariable Integer questionID) {
        try {
            Question_Bank_DTO questionBankDTO = service.getQuestionBankByQuestionID(questionID);
            return new ResponseEntity<>(questionBankDTO, HttpStatus.OK);
        } catch (QuestionBankNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question_Bank_DTO>> getAllQuestionBanks() {
        List<Question_Bank_DTO> questionBankList = service.getAllQuestionBanks();

        if (questionBankList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionBankList, HttpStatus.OK);
    }

    @PutMapping("/update/{questionID}")
    public ResponseEntity<Question_Bank_DTO> updateQuestionBank(@PathVariable Integer questionID,
                                                                @RequestBody Question_Bank_DTO updatedQuestionBankDTO) {
        try {
            Question_Bank_DTO updatedQuestionBank = service.updateQuestionBank(questionID, updatedQuestionBankDTO);
            return new ResponseEntity<>(updatedQuestionBank, HttpStatus.OK);
        } catch (QuestionBankNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{questionID}")
    public ResponseEntity<Void> deleteQuestionBank(@PathVariable Integer questionID) {
        try {
            boolean deleted = service.deleteQuestionBank(questionID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QuestionBankNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
