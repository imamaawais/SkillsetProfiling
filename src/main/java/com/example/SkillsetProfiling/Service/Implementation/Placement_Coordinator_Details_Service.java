package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Placement_Coordinator_Details_DTO;
import com.example.SkillsetProfiling.Entity.Placement_Coordinator_Details;
import com.example.SkillsetProfiling.Exception.CoordinatorDetailsNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateCoordinatorDetailsException;
import com.example.SkillsetProfiling.Repository.Placement_Coordinator_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IPlacement_Coordinator_Details_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Placement_Coordinator_Details_Service implements IPlacement_Coordinator_Details_Service {
    private Placement_Coordinator_Details_Repo placementCoordinatorDetailsRepo;
    private ModelMapper mapper;

    @Override
    public Placement_Coordinator_Details_DTO addCoordinatorDetails(Placement_Coordinator_Details_DTO coordinatorDetailsDTO) {
        Placement_Coordinator_Details coordinatorDetails = mapper.map(coordinatorDetailsDTO, Placement_Coordinator_Details.class);

        // Check if a coordinator with the same ID already exists
        if (placementCoordinatorDetailsRepo.findById(coordinatorDetails.getCoordinatorID()).isPresent())
            throw new DuplicateCoordinatorDetailsException("Coordinator with the same ID already exists: " + coordinatorDetails.getCoordinatorID());

        // Save to the database
        Placement_Coordinator_Details savedCoordinatorDetails = placementCoordinatorDetailsRepo.save(coordinatorDetails);
        Placement_Coordinator_Details_DTO savedCoordinatorDetailsDTO = mapper.map(savedCoordinatorDetails, Placement_Coordinator_Details_DTO.class);
        return savedCoordinatorDetailsDTO;
    }

    @Override
    public Placement_Coordinator_Details_DTO getCoordinatorDetailsByCoordinatorID(Integer coordinatorID) {
        Optional<Placement_Coordinator_Details> coordinatorDetailsOptional = placementCoordinatorDetailsRepo.findById(coordinatorID);

        if (coordinatorDetailsOptional.isPresent()) {
            return mapper.map(coordinatorDetailsOptional.get(), Placement_Coordinator_Details_DTO.class);
        } else {
            throw new CoordinatorDetailsNotFoundException("Coordinator details not found with ID: " + coordinatorID);
        }
    }

    @Override
    public List<Placement_Coordinator_Details_DTO> getAllCoordinatorDetails() {
        List<Placement_Coordinator_Details> allCoordinatorDetails = placementCoordinatorDetailsRepo.findAll();
        return allCoordinatorDetails.stream()
                .map(coordinatorDetails -> mapper.map(coordinatorDetails, Placement_Coordinator_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Placement_Coordinator_Details_DTO updateCoordinatorDetails(Integer coordinatorID, Placement_Coordinator_Details_DTO updatedCoordinatorDetailsDTO) {
        Optional<Placement_Coordinator_Details> existingCoordinatorDetailsOptional = placementCoordinatorDetailsRepo.findById(coordinatorID);

        if (existingCoordinatorDetailsOptional.isPresent()) {
            Placement_Coordinator_Details existingCoordinatorDetails = existingCoordinatorDetailsOptional.get();
            // Update coordinator details based on updatedCoordinatorDetailsDTO
            // Update other fields as needed

            existingCoordinatorDetails.setUserDetails(updatedCoordinatorDetailsDTO.getUserDetails());

            Placement_Coordinator_Details updatedCoordinatorDetails = placementCoordinatorDetailsRepo.save(existingCoordinatorDetails);
            return mapper.map(updatedCoordinatorDetails, Placement_Coordinator_Details_DTO.class);
        } else {
            throw new CoordinatorDetailsNotFoundException("Coordinator details not found with ID: " + coordinatorID);
        }
    }

    @Override
    @Transactional
    public boolean deleteCoordinatorDetails(Integer coordinatorID) {
        Optional<Placement_Coordinator_Details> coordinatorDetailsOptional = placementCoordinatorDetailsRepo.findById(coordinatorID);

        if (coordinatorDetailsOptional.isPresent()) {
            placementCoordinatorDetailsRepo.deleteById(coordinatorID);
            return true; // Deletion successful
        } else {
            throw new CoordinatorDetailsNotFoundException("Coordinator details not found with ID: " + coordinatorID);
        }
    }


}
