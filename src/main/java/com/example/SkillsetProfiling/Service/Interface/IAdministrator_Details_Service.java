package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Administrator_Details_DTO;

import java.util.List;

public interface IAdministrator_Details_Service {
    Administrator_Details_DTO addAdministratorDetails(Administrator_Details_DTO administratorDetailsDTO);

    Administrator_Details_DTO getAdministratorDetailsByAdministratorID(Integer administratorID);

    List<Administrator_Details_DTO> getAllAdministratorDetails();

    Administrator_Details_DTO updateAdministratorDetails(Integer administratorID, Administrator_Details_DTO updatedAdministratorDetailsDTO);

    boolean deleteAdministratorDetails(Integer administratorID);
}
