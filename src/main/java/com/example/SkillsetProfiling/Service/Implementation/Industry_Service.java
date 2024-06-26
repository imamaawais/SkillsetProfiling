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

    private Industry_Repo industryRepo;
    private ModelMapper mapper;

    @Override
    public Industry_DTO addIndustry(Industry_DTO industryDTO) {
        Industry industry = mapper.map(industryDTO, Industry.class);



        Industry savedIndustry = industryRepo.save(industry);
        Industry_DTO savedIndustryDTO = mapper.map(savedIndustry, Industry_DTO.class);
        return savedIndustryDTO;
    }

    @Override
    public List<Industry_DTO> getAllIndustries() {
        List<Industry> industries = industryRepo.findAll();
        return industries.stream()
                .map(industry -> mapper.map(industry, Industry_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Industry_DTO getIndustryByID(Integer industryID) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = industryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            return mapper.map(industryOptional.get(), Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }

    @Override
    public Industry_DTO getIndustryByName(String industryName) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = industryRepo.findByIndustryName(industryName);

        if (industryOptional.isPresent()) {
            return mapper.map(industryOptional.get(), Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with name: " + industryName);
        }
    }

    @Override
    public Industry_DTO updateIndustry(Integer industryID, Industry_DTO updatedIndustryDTO) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = industryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            Industry existingIndustry = industryOptional.get();

            existingIndustry.setIndustryName(updatedIndustryDTO.getIndustryName());
            // Add other properties as needed

            Industry updatedIndustry = industryRepo.save(existingIndustry);
            return mapper.map(updatedIndustry, Industry_DTO.class);
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }

    @Override
    @Transactional
    public boolean deleteIndustry(Integer industryID) throws IndustryNotFoundException {
        Optional<Industry> industryOptional = industryRepo.findById(industryID);

        if (industryOptional.isPresent()) {
            Industry industryToDelete = industryOptional.get();
            industryRepo.delete(industryToDelete);
            return true;
        } else {
            throw new IndustryNotFoundException("Industry not found with ID: " + industryID);
        }
    }
}
