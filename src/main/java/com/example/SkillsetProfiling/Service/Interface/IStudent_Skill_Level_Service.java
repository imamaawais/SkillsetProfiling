package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Student_Skill_Level_DTO;

import java.util.List;

public interface IStudent_Skill_Level_Service {
    Student_Skill_Level_DTO addStudentSkillLevel(Student_Skill_Level_DTO studentSkillLevelDTO);

    Student_Skill_Level_DTO getStudentSkillLevelByStudentSkillLevelID(Integer studentSkillLevelID);

    List<Student_Skill_Level_DTO> getAllStudentSkillLevels();

    Student_Skill_Level_DTO updateStudentSkillLevel(Integer studentSkillLevelID, Student_Skill_Level_DTO updatedStudentSkillLevelDTO);

    boolean deleteStudentSkillLevel(Integer studentSkillLevelID);
}
