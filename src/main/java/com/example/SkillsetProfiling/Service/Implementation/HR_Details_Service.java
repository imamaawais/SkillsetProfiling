package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.HR_Details_DTO;
import com.example.SkillsetProfiling.Entity.HR_Details;
import com.example.SkillsetProfiling.Exception.DuplicateHRDetailsException;
import com.example.SkillsetProfiling.Exception.HRDetailsNotFoundException;
import com.example.SkillsetProfiling.Repository.HR_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IHR_Details_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HR_Details_Service implements IHR_Details_Service {

    private HR_Details_Repo hrDetailsRepo;
    private ModelMapper mapper;

    @Override
    public HR_Details_DTO addHRDetails(HR_Details_DTO hrDetailsDto) {
        HR_Details hr_details = mapper.map(hrDetailsDto, HR_Details.class);

        // Check if a HR with the same ID already exists
        if (hrDetailsRepo.findById(hr_details.getHrID()).isPresent())
            throw new DuplicateHRDetailsException("HR with the same ID already exists: " + hr_details.getHrID());

        // Save to the database
        HR_Details savedHRDetails = hrDetailsRepo.save(hr_details);
        HR_Details_DTO savedHRDetailsDTO = mapper.map(savedHRDetails, HR_Details_DTO.class);
        return savedHRDetailsDTO;
    }

    @Override
    public HR_Details_DTO getHRDetailsByHRID(Integer hrID) {
        Optional<HR_Details> hrDetailsOptional = hrDetailsRepo.findById(hrID);

        if (hrDetailsOptional.isPresent()) {
            return mapper.map(hrDetailsOptional.get(), HR_Details_DTO.class);
        } else {
            throw new HRDetailsNotFoundException("HR details not found with ID: " + hrID);
        }
    }

    @Override
    public List<HR_Details_DTO> getAllHRDetails() {
        List<HR_Details> allHRDetails = hrDetailsRepo.findAll();
        return allHRDetails.stream()
                .map(hrDetails -> mapper.map(hrDetails, HR_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HR_Details_DTO updateHRDetails(Integer hrID, HR_Details_DTO updatedHRDetailsDTO) {
        Optional<HR_Details> existingHRDetailsOptional = hrDetailsRepo.findById(hrID);

        if (existingHRDetailsOptional.isPresent()) {
            HR_Details existingHRDetails = existingHRDetailsOptional.get();
            // Update hr details based on updatedHRDetailsDTO
            existingHRDetails.setUserDetails(updatedHRDetailsDTO.getUserDetails());
            existingHRDetails.setCompanyName(updatedHRDetailsDTO.getCompanyName());
            existingHRDetails.setIndustry(updatedHRDetailsDTO.getIndustry());

            HR_Details updatedHRDetails = hrDetailsRepo.save(existingHRDetails);

            return mapper.map(updatedHRDetails, HR_Details_DTO.class);
        } else {
            throw new HRDetailsNotFoundException("HR details not found with ID: " + hrID);
        }
    }


    @Override
    public boolean deleteHRDetails(Integer hrID) {
        Optional<HR_Details> hrDetailsOptional = hrDetailsRepo.findById(hrID);

        if (hrDetailsOptional.isPresent()) {
            hrDetailsRepo.deleteById(hrID);
            return true; // Deletion successful
        } else {
            throw new HRDetailsNotFoundException("HR details not found with ID: " + hrID);
        }
    }
}
