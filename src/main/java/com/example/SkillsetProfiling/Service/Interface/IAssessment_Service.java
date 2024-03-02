package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Assessment_DTO;

import java.util.List;

public interface IAssessment_Service {
    Assessment_DTO addAssessment(Assessment_DTO assessmentDto);

    Assessment_DTO getAssessmentById(Integer assessmentId);

    List<Assessment_DTO> getAllAssessments();

    Assessment_DTO updateAssessment(Integer assessmentId, Assessment_DTO updatedAssessmentDTO);

    boolean deleteAssessment(Integer assessmentId);
}
