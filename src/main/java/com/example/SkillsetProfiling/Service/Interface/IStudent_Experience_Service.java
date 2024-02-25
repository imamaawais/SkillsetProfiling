package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Student_Experience_DTO;
import com.example.SkillsetProfiling.Exception.StudentExperienceNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateStudentExperienceException;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;

import java.util.List;

public interface IStudent_Experience_Service {
    Student_Experience_DTO addStudentExperience(Student_Experience_DTO studentExperienceDTO) throws DuplicateStudentExperienceException;
    List<Student_Experience_DTO> getAllStudentExperiences();
    Student_Experience_DTO getStudentExperienceByID(Student_Experience_Key studentExperienceKey) throws StudentExperienceNotFoundException;
    List<Student_Experience_DTO> getStudentExperiencesByStudentID(Integer studentID);
    List<Student_Experience_DTO> getStudentExperiencesByExperienceID(Integer experienceID);
    Student_Experience_DTO updateStudentExperience(Student_Experience_Key studentExperienceKey, Student_Experience_DTO updatedStudentExperienceDTO) throws StudentExperienceNotFoundException;
    boolean deleteStudentExperience(Student_Experience_Key studentExperienceKey) throws StudentExperienceNotFoundException;
}
