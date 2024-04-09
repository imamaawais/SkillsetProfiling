package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Sub_Skills_DTO;
import com.example.SkillsetProfiling.Entity.Sub_Skills;
import com.example.SkillsetProfiling.Exception.DuplicateSubSkillException;
import com.example.SkillsetProfiling.Exception.SubSkillNotFoundException;
import com.example.SkillsetProfiling.Repository.Sub_Skills_Repo;
import com.example.SkillsetProfiling.Service.Interface.ISub_Skills_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Sub_Skills_Service implements ISub_Skills_Service {

    private Sub_Skills_Repo subSkillsRepo;
    private ModelMapper mapper;

    @Override
    public Sub_Skills_DTO addSubSkill(Sub_Skills_DTO subSkillsDTO) {
        Sub_Skills subSkill = mapper.map(subSkillsDTO, Sub_Skills.class);



        Sub_Skills savedSubSkill = subSkillsRepo.save(subSkill);
        Sub_Skills_DTO savedSubSkillsDTO = mapper.map(savedSubSkill, Sub_Skills_DTO.class);

        return savedSubSkillsDTO;
    }

    @Override
    public Sub_Skills_DTO getSubSkillById(Integer subSkillID) throws SubSkillNotFoundException {
        Optional<Sub_Skills> subSkillsOptional = subSkillsRepo.findById(subSkillID);

        if (subSkillsOptional.isPresent()) {
            return mapper.map(subSkillsOptional.get(), Sub_Skills_DTO.class);
        } else {
            throw new SubSkillNotFoundException("Sub Skill not found with id: " + subSkillID);
        }
    }

    @Override
    public List<Sub_Skills_DTO> getAllSubSkills() {
        List<Sub_Skills> subSkills = subSkillsRepo.findAll();

        return subSkills.stream()
                .map(subSkill -> mapper.map(subSkill, Sub_Skills_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Sub_Skills_DTO updateSubSkill(Integer subSkillID, Sub_Skills_DTO updatedSubSkillDTO) {
        Optional<Sub_Skills> subSkillsOptional = subSkillsRepo.findById(subSkillID);

        if (subSkillsOptional.isPresent()) {
            Sub_Skills existingSubSkill = subSkillsOptional.get();

            existingSubSkill.setSubSkillName(updatedSubSkillDTO.getSubSkillName());
            existingSubSkill.setSubSkillDescription(updatedSubSkillDTO.getSubSkillDescription());
            existingSubSkill.setSkills(updatedSubSkillDTO.getSkills());

            Sub_Skills updatedSubSkill = subSkillsRepo.save(existingSubSkill);

            return mapper.map(updatedSubSkill, Sub_Skills_DTO.class);
        } else {
            throw new SubSkillNotFoundException("Sub Skill not found with ID: " + subSkillID);
        }
    }

    @Override
    public boolean deleteSubSkill(Integer subSkillID) {
        Optional<Sub_Skills> subSkillsOptional = subSkillsRepo.findById(subSkillID);

        if (subSkillsOptional.isPresent()) {
            Sub_Skills subSkillToDelete = subSkillsOptional.get();
            subSkillsRepo.delete(subSkillToDelete);
            return true; // Deletion successful
        } else {
            throw new SubSkillNotFoundException("Sub Skill not found with ID: " + subSkillID);
        }
    }
}
