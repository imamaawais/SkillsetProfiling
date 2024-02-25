package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Skills_DTO;
import com.example.SkillsetProfiling.Entity.Skills;
import com.example.SkillsetProfiling.Exception.SkillNotFoundException;

import java.util.List;

public interface ISkills_Service {
    Skills_DTO addSkill(Skills_DTO skillsDTO);
    Skills_DTO getSkillById(Integer SkillID) throws SkillNotFoundException;
    List<Skills_DTO> getAllSkills();
    Skills_DTO updateSkill(Integer SkillID, Skills_DTO updatedSkillDTO);
    boolean deleteSkill(Integer SkillID);
}
