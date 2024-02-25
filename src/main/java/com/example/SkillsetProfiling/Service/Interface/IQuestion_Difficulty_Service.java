package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Question_Difficulty_DTO;

import java.util.List;

public interface IQuestion_Difficulty_Service {
    Question_Difficulty_DTO addQuestionDifficulty(Question_Difficulty_DTO questionDifficultyDTO);

    Question_Difficulty_DTO getQuestionDifficultyByDifficultyID(Integer difficultyID);

    List<Question_Difficulty_DTO> getAllQuestionDifficulties();

    Question_Difficulty_DTO updateQuestionDifficulty(Integer difficultyID, Question_Difficulty_DTO updatedQuestionDifficultyDTO);

    boolean deleteQuestionDifficulty(Integer difficultyID);
}
