package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Experience_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateExperienceException;
import com.example.SkillsetProfiling.Exception.ExperienceNotFoundException;

import java.util.List;

public interface IExperience_Service {
    Experience_DTO addExperience(Experience_DTO experienceDto) throws DuplicateExperienceException;
    Experience_DTO getExperienceByID(Integer experienceId) throws ExperienceNotFoundException;
    List<Experience_DTO> getAllExperiences();
    Experience_DTO updateExperience(Integer experienceId, Experience_DTO updatedExperienceDto) throws ExperienceNotFoundException;
    boolean deleteExperience(Integer experienceId);
}
