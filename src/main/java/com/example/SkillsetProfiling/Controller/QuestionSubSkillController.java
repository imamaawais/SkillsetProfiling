package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Question_Sub_Skill_DTO;
import com.example.SkillsetProfiling.Exception.QuestionSubSkillNotFoundException;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Sub_Skill_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/question_sub_skill")
public class QuestionSubSkillController {

    private final IQuestion_Sub_Skill_Service service;

    @PostMapping("/add")
    public ResponseEntity<Question_Sub_Skill_DTO> addQuestionSubSkill(@RequestBody Question_Sub_Skill_DTO questionSubSkillDTO) {
        Question_Sub_Skill_DTO savedQuestionSubSkill = service.addQuestionSubSkill(questionSubSkillDTO);
        return new ResponseEntity<>(savedQuestionSubSkill, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question_Sub_Skill_DTO>> getAllQuestionSubSkills() {
        List<Question_Sub_Skill_DTO> questionSubSkills = service.getAllQuestionSubSkills();

        if (questionSubSkills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionSubSkills, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{QuestionID}/{SubSkillID}")
    public ResponseEntity<Question_Sub_Skill_DTO> getQuestionSubSkillById(@PathVariable int QuestionID, @PathVariable int SubSkillID) {
        try {
            Question_Sub_Skill_DTO questionSubSkillDTO = service.getQuestionSubSkillById(QuestionID, SubSkillID);
            return new ResponseEntity<>(questionSubSkillDTO, HttpStatus.OK);
        } catch (QuestionSubSkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{QuestionID}/{SubSkillID}")
    public ResponseEntity<Question_Sub_Skill_DTO> updateQuestionSubSkill(@PathVariable Integer QuestionID, @PathVariable Integer SubSkillID, @RequestBody Question_Sub_Skill_DTO updatedQuestionSubSkillDTO) {
        try {
            Question_Sub_Skill_DTO updatedQuestionSubSkill = service.updateQuestionSubSkill(QuestionID, SubSkillID, updatedQuestionSubSkillDTO);
            return new ResponseEntity<>(updatedQuestionSubSkill, HttpStatus.OK);
        } catch (QuestionSubSkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{QuestionID}/{SubSkillID}")
    public ResponseEntity<Void> deleteQuestionSubSkill(@PathVariable Integer QuestionID, @PathVariable Integer SubSkillID) {
        try {
            boolean deleted = service.deleteQuestionSubSkill(QuestionID, SubSkillID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (QuestionSubSkillNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
