package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Sub_Skills_DTO;
import com.example.SkillsetProfiling.Entity.Sub_Skills;
import com.example.SkillsetProfiling.Exception.SubSkillNotFoundException;

import java.util.List;

public interface ISub_Skills_Service {

    Sub_Skills_DTO addSubSkill(Sub_Skills_DTO subSkillsDTO);
    Sub_Skills_DTO getSubSkillById(Integer subSkillID) throws SubSkillNotFoundException;
    List<Sub_Skills_DTO> getAllSubSkills();
    Sub_Skills_DTO updateSubSkill(Integer subSkillID, Sub_Skills_DTO updatedSubSkillDTO);
    List<Sub_Skills_DTO> getSubSkillsBySkillID(Integer skillID);
    boolean deleteSubSkill(Integer subSkillID);

}
