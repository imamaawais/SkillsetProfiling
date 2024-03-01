package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Question_Sub_Skill_DTO;
import com.example.SkillsetProfiling.Exception.QuestionSubSkillNotFoundException;

import java.util.List;

public interface IQuestion_Sub_Skill_Service {

    Question_Sub_Skill_DTO addQuestionSubSkill(Question_Sub_Skill_DTO questionSubSkillDTO);

    Question_Sub_Skill_DTO getQuestionSubSkillById(Integer questionId, Integer subSkillId) throws QuestionSubSkillNotFoundException;

    List<Question_Sub_Skill_DTO> getAllQuestionSubSkills();

    Question_Sub_Skill_DTO updateQuestionSubSkill(Integer questionId, Integer subSkillId, Question_Sub_Skill_DTO updatedQuestionSubSkillDTO);

    boolean deleteQuestionSubSkill(Integer questionId, Integer subSkillId);
}
