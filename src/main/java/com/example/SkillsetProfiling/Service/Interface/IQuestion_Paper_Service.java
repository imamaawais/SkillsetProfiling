package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Question_Paper_DTO;
import com.example.SkillsetProfiling.Exception.QuestionPaperNotFoundException;

import java.util.List;

public interface IQuestion_Paper_Service {

    Question_Paper_DTO addQuestionPaper(Question_Paper_DTO questionPaperDTO);

    Question_Paper_DTO getQuestionPaperById(Integer questionPaperId) throws QuestionPaperNotFoundException;

    Question_Paper_DTO getRandomQuestionPaper(Integer skillID, Integer skillLevelID);

    List<Question_Paper_DTO> getAllQuestionPapers();

    Question_Paper_DTO updateQuestionPaper(Integer questionPaperId, Question_Paper_DTO updatedQuestionPaperDTO);

    boolean deleteQuestionPaper(Integer questionPaperId);
}
