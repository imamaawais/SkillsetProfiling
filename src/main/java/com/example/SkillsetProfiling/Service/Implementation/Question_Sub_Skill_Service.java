package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Sub_Skill_DTO;
import com.example.SkillsetProfiling.Entity.Question_Sub_Skill;
import com.example.SkillsetProfiling.Exception.QuestionSubSkillNotFoundException;
import com.example.SkillsetProfiling.Key.Question_Sub_Skill_Key;
import com.example.SkillsetProfiling.Repository.Question_Sub_Skill_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Sub_Skill_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Question_Sub_Skill_Service implements IQuestion_Sub_Skill_Service {

    private final Question_Sub_Skill_Repo questionSubSkillRepo;
    private final ModelMapper mapper;

    @Override
    public Question_Sub_Skill_DTO addQuestionSubSkill(Question_Sub_Skill_DTO questionSubSkillDTO) {
        Question_Sub_Skill questionSubSkill = mapper.map(questionSubSkillDTO, Question_Sub_Skill.class);

        // Save to DB
        Question_Sub_Skill savedQuestionSubSkill = questionSubSkillRepo.save(questionSubSkill);
        return mapper.map(savedQuestionSubSkill, Question_Sub_Skill_DTO.class);
    }

    @Override
    public Question_Sub_Skill_DTO getQuestionSubSkillById(Integer questionId, Integer subSkillId) throws QuestionSubSkillNotFoundException {
        Optional<Question_Sub_Skill> questionSubSkillOptional = questionSubSkillRepo.findById(new Question_Sub_Skill_Key(questionId, subSkillId));
        if (questionSubSkillOptional.isPresent()) {
            return mapper.map(questionSubSkillOptional.get(), Question_Sub_Skill_DTO.class);
        } else {
            throw new QuestionSubSkillNotFoundException("Question-SubSkill association not found with QuestionID: " + questionId + " and SubSkillID: " + subSkillId);
        }
    }

    @Override
    public List<Question_Sub_Skill_DTO> getAllQuestionSubSkills() {
        List<Question_Sub_Skill> questionSubSkills = questionSubSkillRepo.findAll();
        return questionSubSkills.stream()
                .map(questionSubSkill -> mapper.map(questionSubSkill, Question_Sub_Skill_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Sub_Skill_DTO updateQuestionSubSkill(Integer questionId, Integer subSkillId, Question_Sub_Skill_DTO updatedQuestionSubSkillDTO) throws QuestionSubSkillNotFoundException {
        Optional<Question_Sub_Skill> questionSubSkillOptional = questionSubSkillRepo.findById(new Question_Sub_Skill_Key(questionId, subSkillId));
        if (questionSubSkillOptional.isPresent()) {
            Question_Sub_Skill existingQuestionSubSkill = questionSubSkillOptional.get();
            // Update fields as needed
//            existingQuestionSubSkill.setQuestionID(updatedQuestionSubSkillDTO.getQuestionBank());
//            existingQuestionSubSkill.setSubSkills(updatedQuestionSubSkillDTO.getSubSkills());

            Question_Sub_Skill updatedQuestionSubSkill = questionSubSkillRepo.save(existingQuestionSubSkill);
            return mapper.map(updatedQuestionSubSkill, Question_Sub_Skill_DTO.class);
        } else {
            throw new QuestionSubSkillNotFoundException("Question-SubSkill association not found with QuestionID: " + questionId + " and SubSkillID: " + subSkillId);
        }
    }

    @Override
    public boolean deleteQuestionSubSkill(Integer questionId, Integer subSkillId) throws QuestionSubSkillNotFoundException {
        Optional<Question_Sub_Skill> questionSubSkillOptional = questionSubSkillRepo.findById(new Question_Sub_Skill_Key(questionId, subSkillId));
        if (questionSubSkillOptional.isPresent()) {
            questionSubSkillRepo.delete(questionSubSkillOptional.get());
            return true;
        } else {
            throw new QuestionSubSkillNotFoundException("Question-SubSkill association not found with QuestionID: " + questionId + " and SubSkillID: " + subSkillId);
        }
    }
}
