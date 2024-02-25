package com.example.SkillsetProfiling.Service.Implementation;
import com.example.SkillsetProfiling.Dto.Skills_DTO;
import com.example.SkillsetProfiling.Entity.Skills;
import com.example.SkillsetProfiling.Exception.DuplicateSkillException;
import com.example.SkillsetProfiling.Exception.SkillNotFoundException;
import com.example.SkillsetProfiling.Repository.Skills_Repo;
import com.example.SkillsetProfiling.Service.Interface.ISkills_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Skills_Service implements ISkills_Service {
    private Skills_Repo skillsRepo;
    private ModelMapper mapper;

    @Override
    public Skills_DTO addSkill(Skills_DTO skillsDTO) {
        Skills skill = mapper.map(skillsDTO, Skills.class);

        if (skillsRepo.findById(skill.getSkillID()).isPresent()) {
            throw new DuplicateSkillException("Skill with the same Id already exists: " + skill.getSkillID());
        }

        Skills savedSkill = skillsRepo.save(skill);
        Skills_DTO savedSkillsDTO = mapper.map(savedSkill, Skills_DTO.class);

        return savedSkillsDTO;
    }

    @Override
    public Skills_DTO getSkillById(Integer SkillID) throws SkillNotFoundException {
        Optional<Skills> skillsOptional = skillsRepo.findById(SkillID);

        if (skillsOptional.isPresent()) {
            return mapper.map(skillsOptional.get(), Skills_DTO.class);
        } else {
            throw new SkillNotFoundException("Skill not found with id: " + SkillID);
        }
    }

    @Override
    public List<Skills_DTO> getAllSkills() {
        List<Skills> skills = skillsRepo.findAll();

        System.out.println(skills);
        return skills.stream()
                .map(skill -> mapper.map(skill, Skills_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Skills_DTO updateSkill(Integer SkillID, Skills_DTO updatedSkillDTO) {

        Optional<Skills> skillsOptional = skillsRepo.findById(SkillID);

        if (skillsOptional.isPresent()) {
            Skills existingSkill = skillsOptional.get();

            existingSkill.setSkillName(updatedSkillDTO.getSkillName());
            existingSkill.setSkillGroup(updatedSkillDTO.getSkillGroup());
            existingSkill.setSkillDescription(updatedSkillDTO.getSkillDescription());

            Skills updatedSkill = skillsRepo.save(existingSkill);

            return mapper.map(updatedSkill, Skills_DTO.class);
        } else {
            throw new SkillNotFoundException("Skill not found with ID: " + SkillID);
        }
    }

    @Override
    public boolean deleteSkill(Integer SkillID) {
        Optional<Skills> skillsOptional = skillsRepo.findById(SkillID);

        if (skillsOptional.isPresent()) {
            Skills skillToDelete = skillsOptional.get();
            skillsRepo.delete(skillToDelete);
            return true; // Deletion successful
        } else {
            throw new SkillNotFoundException("Skill not found with ID: " + SkillID);
        }
    }

}

