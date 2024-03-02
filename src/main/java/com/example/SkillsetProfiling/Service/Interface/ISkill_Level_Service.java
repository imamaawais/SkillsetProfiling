package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Skill_Level_DTO;
import com.example.SkillsetProfiling.Exception.SkillLevelNotFoundException;

import java.util.List;

public interface ISkill_Level_Service {

    Skill_Level_DTO addSkillLevel(Skill_Level_DTO skillLevelDTO);
    Skill_Level_DTO getSkillLevelById(Integer SkillLevelID) throws SkillLevelNotFoundException;
    List<Skill_Level_DTO> getAllSkillLevels();
    Skill_Level_DTO updateSkillLevel(Integer SkillLevelID, Skill_Level_DTO updatedSkillLevelDTO);
    boolean deleteSkillLevel(Integer SkillLevelID);
}
