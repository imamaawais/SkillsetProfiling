package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Question_Paper_Questions_DTO;

import java.util.List;

public interface IQuestion_Paper_Questions_Service {
    Question_Paper_Questions_DTO addQuestionPaperQuestions(Question_Paper_Questions_DTO questionPaperQuestionsDTO);

    Question_Paper_Questions_DTO getQuestionPaperQuestionsByIds(Integer questionPaperId, Integer questionId);

    List<Question_Paper_Questions_DTO> getAllQuestionPaperQuestions();

    Question_Paper_Questions_DTO updateQuestionPaperQuestions(Integer questionPaperId, Integer questionId, Question_Paper_Questions_DTO updatedQuestionPaperQuestionsDTO);

    boolean deleteQuestionPaperQuestions(Integer questionPaperId, Integer questionId);
}
