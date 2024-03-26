package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Student_Skills_DTO;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;

import java.util.List;

public interface IStudent_Skills_Service {
    Student_Skills_DTO addStudentSkills(Student_Skills_DTO studentSkillsDTO);
    List<Student_Skills_DTO> getAllStudentSkills();
    Student_Skills_DTO getStudentSkillsById(Student_Skills_Key key);
    List<Student_Skills_DTO> getStudentSkillsByStudentId(Integer StudentID);
    Student_Skills_DTO updateStudentSkills(Student_Skills_Key key, Student_Skills_DTO updatedStudentSkillsDTO);
    boolean deleteStudentSkills(Student_Skills_Key key);
}
