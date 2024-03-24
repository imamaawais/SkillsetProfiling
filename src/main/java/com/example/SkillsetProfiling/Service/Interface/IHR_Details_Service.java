package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.HR_Details_DTO;

import java.util.List;


public interface IHR_Details_Service {
    HR_Details_DTO addHRDetails(HR_Details_DTO hrDetailsDto);
    HR_Details_DTO getHRDetailsByHRID(Integer hrID);
    Integer getHRIDByEmail(String email);
    List<HR_Details_DTO> getAllHRDetails();
    HR_Details_DTO updateHRDetails(Integer hrID, HR_Details_DTO updatedHRDetailsDTO );
    boolean deleteHRDetails(Integer hrID);
}
