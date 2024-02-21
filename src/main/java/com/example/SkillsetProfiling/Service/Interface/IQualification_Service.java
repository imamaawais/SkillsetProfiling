package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Qualification_DTO;

import java.util.List;

public interface IQualification_Service {
    Qualification_DTO addQualification(Qualification_DTO qualificationDTO);
    List<Qualification_DTO> getAllQualifications();
    Qualification_DTO getQualificationByID(Integer qualificationID) ;
    Qualification_DTO getQualificationByName(String qualificationName);
    Qualification_DTO updateQualification(Integer qualificationID, Qualification_DTO updatedQualificationDTO);
    boolean deleteQualification(Integer qualificationID);


}
