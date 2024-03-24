package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Placement_Coordinator_Details_DTO;

import java.util.List;

public interface IPlacement_Coordinator_Details_Service {

    Placement_Coordinator_Details_DTO addCoordinatorDetails(Placement_Coordinator_Details_DTO coordinatorDetailsDTO);

    Placement_Coordinator_Details_DTO getCoordinatorDetailsByCoordinatorID(Integer coordinatorID);

    Integer getCoordinatorIDByEmail(String email);

    List<Placement_Coordinator_Details_DTO> getAllCoordinatorDetails();

    Placement_Coordinator_Details_DTO updateCoordinatorDetails(Integer coordinatorID, Placement_Coordinator_Details_DTO updatedCoordinatorDetailsDTO);

    boolean deleteCoordinatorDetails(Integer coordinatorID);
}
