package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Industry_DTO;
import com.example.SkillsetProfiling.Entity.Industry;
import com.example.SkillsetProfiling.Exception.DuplicateIndustryException;
import com.example.SkillsetProfiling.Exception.IndustryNotFoundException;
import com.example.SkillsetProfiling.Repository.Industry_Repo;
import com.example.SkillsetProfiling.Service.Interface.IIndustry_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Industry_Service implements IIndustry_Service {

    private Industry_Repo IndustryRepo;
    private ModelMapper mapper;

    @Override
    public Industry_DTO addIndustry(Industry_DTO industryDTO) {
        Industry industry = mapper.map(industryDTO, Industry.class);

        if (IndustryRepo.findById(industry.getIndustryID()).isPresent()) {
            throw new DuplicateIndustryException("Industry with the same ID already exists: " + industry.getIndustryID());
        }

        Industry savedIndustry = IndustryRepo.save(industry);
        Industry_DTO savedIndustryDTO = mapper.map(savedIndustry, Industry_DTO.class);
        return savedIndustryDTO;
    }

    @Override
    public List<Industry_DTO> getAllIndustries() {
        List<Industry> industries = IndustryRepo.findAll();
        return industries.stream()
                .map(industry -> mapper.map(industry, Industry_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Industry_DTO getIndustryByID(Integer industryID) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = IndustryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            return mapper.map(industryOptional.get(), Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }

    @Override
    public Industry_DTO getIndustryByName(String industryName) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = IndustryRepo.findByIndustryName(industryName);

        if (industryOptional.isPresent()) {
            return mapper.map(industryOptional.get(), Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with name: " + industryName);
        }
    }

    @Override
    public Industry_DTO updateIndustry(Integer industryID, Industry_DTO updatedIndustryDTO) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = IndustryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            Industry existingIndustry = industryOptional.get();

            existingIndustry.setIndustryName(updatedIndustryDTO.getIndustryName());
            // Add other properties as needed

            Industry updatedIndustry = IndustryRepo.save(existingIndustry);
            return mapper.map(updatedIndustry, Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }

    @Override
    @Transactional
    public boolean deleteIndustry(Integer industryID) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = IndustryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            Industry industryToDelete = industryOptional.get();
            IndustryRepo.delete(industryToDelete);
            return true;
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }
}
