package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Assessment_Scores_DTO;

import java.util.List;

public interface IAssessment_Scores_Service {
    Assessment_Scores_DTO addAssessmentScore(Assessment_Scores_DTO assessmentScoresDTO);

    Assessment_Scores_DTO getAssessmentScoreByIds(Integer assessmentId, Integer questionId);

    List<Assessment_Scores_DTO> getAssessmentScoresByAssessmentID(Integer assessmentID);

    List<Assessment_Scores_DTO> getAllAssessmentScores();

    Assessment_Scores_DTO updateAssessmentScore(Integer assessmentId, Integer questionId, Assessment_Scores_DTO updatedAssessmentScoresDTO);

    boolean deleteAssessmentScore(Integer assessmentId, Integer questionId);
}
