package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Student_Details_DTO;

import java.util.List;

public interface IStudent_Details_Service {
    Student_Details_DTO addStudentDetails(Student_Details_DTO studentDetailsDTO);
    Student_Details_DTO getStudentDetailsByStudentID(Integer studentID);
    List<Student_Details_DTO> getAllStudentDetails();
    Student_Details_DTO updateStudentDetails(Integer studentID, Student_Details_DTO updatedStudentDetailsDTO);
    boolean deleteStudentDetails(Integer studentID);
    Integer findStudentIdByEmail(String email);
}
