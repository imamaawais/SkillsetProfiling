package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Question_Bank_DTO;

import java.util.List;

public interface IQuestion_Bank_Service {
    Question_Bank_DTO addQuestionBank(Question_Bank_DTO questionBankDTO);

    Question_Bank_DTO getQuestionBankByQuestionID(Integer questionID);

    List<Question_Bank_DTO> getQuestionsBySkillAndLevel(Integer skillID, Integer difficultyID);

    List<Question_Bank_DTO> getAllQuestionBanks();

    Question_Bank_DTO updateQuestionBank(Integer questionID, Question_Bank_DTO updatedQuestionBankDTO);

    boolean deleteQuestionBank(Integer questionID);
}
