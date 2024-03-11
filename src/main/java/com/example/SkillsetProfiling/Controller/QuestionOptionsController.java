//package com.example.SkillsetProfiling.Controller;
//
//
//import com.example.SkillsetProfiling.Dto.Question_Options_DTO;
//import com.example.SkillsetProfiling.Exception.QuestionOptionsNotFoundException;
//import com.example.SkillsetProfiling.Service.Implementation.Question_Bank_Service;
//import com.example.SkillsetProfiling.Service.Implementation.Question_Options_Service;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@AllArgsConstructor
//@CrossOrigin("*")
//@RestController
//@RequestMapping("api/question_options")
//public class QuestionOptionsController {
//    private Question_Options_Service service;
//
//
//    @PostMapping("/add")
//    public ResponseEntity<Question_Options_DTO> addQuestionOptions(@RequestBody Question_Options_DTO questionOptionsDTO) {
//        Question_Options_DTO addedQuestionOptions = service.addQuestionOption(questionOptionsDTO);
//        return new ResponseEntity<>(addedQuestionOptions, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/getFromId/{questionID}")
//    public ResponseEntity<Question_Options_DTO> getQuestionOptionsByQuestionID(@PathVariable Integer questionID) {
//        try {
//            Question_Options_DTO questionOptionsDTO = service.getQuestionOptionByQuestionID(questionID);
//            return new ResponseEntity<>(questionOptionsDTO, HttpStatus.OK);
//        } catch (QuestionOptionsNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Question_Options_DTO>> getAllQuestionOptions() {
//        List<Question_Options_DTO> questionOptionsList = service.getAllQuestionOptions();
//
//        if (questionOptionsList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(questionOptionsList, HttpStatus.OK);
//    }
//
//    @PutMapping("/update/{questionID}")
//    public ResponseEntity<Question_Options_DTO> updateQuestionOptions(@PathVariable Integer questionID,
//                                                                @RequestBody Question_Options_DTO updatedQuestionOptionsDTO) {
//        try {
//            Question_Options_DTO updatedQuestionOptions = service.updateQuestionOption(questionID, updatedQuestionOptionsDTO);
//            return new ResponseEntity<>(updatedQuestionOptions, HttpStatus.OK);
//        } catch (QuestionOptionsNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/delete/{questionID}")
//    public ResponseEntity<Void> deleteQuestionOptions(@PathVariable Integer questionID) {
//        try {
//            boolean deleted = service.deleteQuestionOption(questionID);
//            if (deleted) {
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (QuestionOptionsNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
