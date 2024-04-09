package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Qualification_DTO;
import com.example.SkillsetProfiling.Entity.Qualification;
import com.example.SkillsetProfiling.Exception.DuplicateQualificationException;
import com.example.SkillsetProfiling.Exception.QualificationNotFoundException;
import com.example.SkillsetProfiling.Repository.Qualification_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQualification_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Qualification_Service implements IQualification_Service {

    private Qualification_Repo qualificationRepo;
    private ModelMapper mapper;

    @Override
    public Qualification_DTO addQualification(Qualification_DTO qualificationDTO) {
        // Map the QualificationDTO to the Qualification entity
        Qualification qualification = mapper.map(qualificationDTO, Qualification.class);



        // No qualification with the same ID, proceed to save the new qualification
        Qualification savedQualification = qualificationRepo.save(qualification);
        Qualification_DTO savedQualificationDTO = mapper.map(savedQualification, Qualification_DTO.class);
        // Map the saved Qualification entity back to QualificationDTO
        return savedQualificationDTO;
    }

    @Override
    public List<Qualification_DTO> getAllQualifications() {
        List<Qualification> qualifications = qualificationRepo.findAll();
        return qualifications.stream()
                .map(qualification -> mapper.map(qualification, Qualification_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Qualification_DTO getQualificationByID(Integer qualificationID)  {
        Optional<Qualification> qualificationOptional = qualificationRepo.findById(qualificationID);

        if (qualificationOptional.isPresent()) {
            return mapper.map(qualificationOptional.get(), Qualification_DTO.class);
        } else {
            throw new QualificationNotFoundException("Qualification not found with ID: " + qualificationID);
        }
    }

    @Override
    public Qualification_DTO getQualificationByName(String qualificationName) {
        Optional<Qualification> qualificationOptional = qualificationRepo.findByQualificationName(qualificationName);

        if (qualificationOptional.isPresent()) {
            return mapper.map(qualificationOptional.get(), Qualification_DTO.class);
        } else {
            throw new QualificationNotFoundException("Qualification not found with name: " + qualificationName);
        }
    }

    @Override
    public Qualification_DTO updateQualification(Integer qualificationID, Qualification_DTO updatedQualificationDTO) throws QualificationNotFoundException {
        Optional<Qualification> qualificationOptional = qualificationRepo.findById(qualificationID);

        if (qualificationOptional.isPresent()) {
            Qualification existingQualification = qualificationOptional.get();

            // Update the properties of the existing qualification with the values from the updatedQualificationDTO
            existingQualification.setQualificationName(updatedQualificationDTO.getQualificationName());
            // Add other properties as needed

            // Save the updated qualification
            Qualification updatedQualification = qualificationRepo.save(existingQualification);

            // Map the updated qualification to Qualification_DTO and return
            return mapper.map(updatedQualification, Qualification_DTO.class);
        } else {
            throw new QualificationNotFoundException("Qualification not found with ID: " + qualificationID);
        }
    }

    @Override
    @Transactional
    public boolean deleteQualification(Integer qualificationID) {
        Optional<Qualification> qualificationOptional = qualificationRepo.findById(qualificationID);

        if (qualificationOptional.isPresent()) {
            Qualification qualificationToDelete = qualificationOptional.get();
            qualificationRepo.delete(qualificationToDelete);
            return true; // Deletion successful
        } else {
            throw new QualificationNotFoundException("Qualification not found with ID: " + qualificationID);
        }
    }
}
