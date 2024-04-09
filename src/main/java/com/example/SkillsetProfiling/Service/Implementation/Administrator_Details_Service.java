package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Administrator_Details_DTO;
import com.example.SkillsetProfiling.Entity.Administrator_Details;
import com.example.SkillsetProfiling.Exception.AdministratorDetailsNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateAdministratorDetailsException;
import com.example.SkillsetProfiling.Exception.StudentDetailsNotFoundException;
import com.example.SkillsetProfiling.Repository.Administrator_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAdministrator_Details_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Administrator_Details_Service implements IAdministrator_Details_Service {
    private Administrator_Details_Repo administratorDetailsRepo;
    private ModelMapper mapper;


    @Override
    public Administrator_Details_DTO addAdministratorDetails(Administrator_Details_DTO administratorDetailsDTO) {
        Administrator_Details administratorDetails = mapper.map(administratorDetailsDTO, Administrator_Details.class);


        // Save to the database
        Administrator_Details savedAdministratorDetails = administratorDetailsRepo.save(administratorDetails);
        Administrator_Details_DTO savedAdministratorDetailsDTO = mapper.map(savedAdministratorDetails, Administrator_Details_DTO.class);
        return savedAdministratorDetailsDTO;
    }

    @Override
    public Administrator_Details_DTO getAdministratorDetailsByAdministratorID(Integer administratorID) {
        Optional<Administrator_Details> administratorDetailsOptional = administratorDetailsRepo.findById(administratorID);

        if (administratorDetailsOptional.isPresent()) {
            return mapper.map(administratorDetailsOptional.get(), Administrator_Details_DTO.class);
        } else {
            throw new AdministratorDetailsNotFoundException("Administrator details not found with ID: " + administratorID);
        }
    }

    @Override
    public Integer getAdministratorIDByEmail(String email) {
        Optional<Integer> administratorDetailsOptional = administratorDetailsRepo.findByUserDetails_Auth_Email(email);

        if (administratorDetailsOptional.isPresent()) {
            return administratorDetailsOptional.get();
        } else {
            throw new StudentDetailsNotFoundException("Administrator details not found with email: " + email);
        }
    }

    @Override
    public List<Administrator_Details_DTO> getAllAdministratorDetails() {
        List<Administrator_Details> allAdministratorDetails = administratorDetailsRepo.findAll();
        return allAdministratorDetails.stream()
                .map(administratorDetails -> mapper.map(administratorDetails, Administrator_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Administrator_Details_DTO updateAdministratorDetails(Integer administratorID, Administrator_Details_DTO updatedAdministratorDetailsDTO) {
        Optional<Administrator_Details> existingAdministratorDetailsOptional = administratorDetailsRepo.findById(administratorID);

        if (existingAdministratorDetailsOptional.isPresent()) {
            Administrator_Details existingAdministratorDetails = existingAdministratorDetailsOptional.get();
            // Update administrator details based on updatedAdministratorDetailsDTO
            // Update other fields as needed

            existingAdministratorDetails.setUserDetails(updatedAdministratorDetailsDTO.getUserDetails());

            Administrator_Details updatedAdministratorDetails = administratorDetailsRepo.save(existingAdministratorDetails);
            return mapper.map(updatedAdministratorDetails, Administrator_Details_DTO.class);
        } else {
            throw new AdministratorDetailsNotFoundException("Administrator details not found with ID: " + administratorID);
        }
    }

    @Override
    @Transactional
    public boolean deleteAdministratorDetails(Integer administratorID) {
        Optional<Administrator_Details> administratorDetailsOptional = administratorDetailsRepo.findById(administratorID);

        if (administratorDetailsOptional.isPresent()) {
            administratorDetailsRepo.deleteById(administratorID);
            return true; // Deletion successful
        } else {
            throw new AdministratorDetailsNotFoundException("Administrator details not found with ID: " + administratorID);
        }
    }

}
