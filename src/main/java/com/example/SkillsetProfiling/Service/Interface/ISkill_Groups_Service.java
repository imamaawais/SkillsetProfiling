package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Auth_DTO;
import com.example.SkillsetProfiling.Dto.Skill_Groups_DTO;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;

import java.util.List;

public interface ISkill_Groups_Service {

    Skill_Groups_DTO addSkillGroup(Skill_Groups_DTO skillGroupsDTO);
    Skill_Groups_DTO getSkillGroupById(Integer SkillGroupID) throws UserNotFoundException;
    List<Skill_Groups_DTO> getAllSkillGroups();
    Skill_Groups_DTO updateSkillGroup(String SkillGroupID, Skill_Groups_DTO updatedSkillGroupsDTO);

    boolean deleteSkillGroup(Integer SkillGroupID);
}
