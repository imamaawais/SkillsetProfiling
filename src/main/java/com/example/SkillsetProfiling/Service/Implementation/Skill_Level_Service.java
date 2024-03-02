package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Skill_Level_DTO;
import com.example.SkillsetProfiling.Entity.Skill_Level;
import com.example.SkillsetProfiling.Exception.DuplicateSkillLevelException;
import com.example.SkillsetProfiling.Exception.SkillLevelNotFoundException;
import com.example.SkillsetProfiling.Repository.Skill_Level_Repo;
import com.example.SkillsetProfiling.Service.Interface.ISkill_Level_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Skill_Level_Service implements ISkill_Level_Service {

    private Skill_Level_Repo skillLevelRepo;
    private ModelMapper mapper;
    @Override
    public Skill_Level_DTO addSkillLevel(Skill_Level_DTO skillLevelDTO) {
        Skill_Level skillLevel = mapper.map(skillLevelDTO, Skill_Level.class);

        if (skillLevelRepo.findById(skillLevel.getLevelID()).isPresent()) {
            throw new DuplicateSkillLevelException("Skill Level with the same Id already exists: " + skillLevel.getLevelID());
        }

        Skill_Level savedSkillLevel = skillLevelRepo.save(skillLevel);
        Skill_Level_DTO savedSkillLevelDTO = mapper.map(savedSkillLevel, Skill_Level_DTO.class);

        return savedSkillLevelDTO;
    }

    @Override
    public Skill_Level_DTO getSkillLevelById(Integer SkillLevelID) throws SkillLevelNotFoundException {
        Optional<Skill_Level> skillLevelOptional = skillLevelRepo.findById(SkillLevelID);

        if (skillLevelOptional.isPresent()) {
            return mapper.map(skillLevelOptional.get(), Skill_Level_DTO.class);
        } else {
            throw new SkillLevelNotFoundException("Skill Level not found with id: " + SkillLevelID);
        }
    }

    @Override
    public List<Skill_Level_DTO> getAllSkillLevels() {
        List<Skill_Level> skillLevels = skillLevelRepo.findAll();

        return skillLevels.stream()
                .map(skillLevel -> mapper.map(skillLevel, Skill_Level_DTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public Skill_Level_DTO updateSkillLevel(Integer SkillLevelID, Skill_Level_DTO updatedSkillLevelDTO) {
        Optional<Skill_Level> skillLevelOptional = skillLevelRepo.findById(SkillLevelID);

        if (skillLevelOptional.isPresent()) {
            Skill_Level existingSkillLevel = skillLevelOptional.get();

            existingSkillLevel.setLevelName(updatedSkillLevelDTO.getLevelName());
            existingSkillLevel.setNoTestsRequired(updatedSkillLevelDTO.getNoTestsRequired());

            Skill_Level updatedSkillLevel = skillLevelRepo.save(existingSkillLevel);

            return mapper.map(updatedSkillLevel, Skill_Level_DTO.class);
        } else {
            throw new SkillLevelNotFoundException("Skill Level not found with ID: " + SkillLevelID);
        }
    }

    @Override
    public boolean deleteSkillLevel(Integer SkillLevelID) {
        Optional<Skill_Level> skillLevelOptional = skillLevelRepo.findById(SkillLevelID);

        if (skillLevelOptional.isPresent()) {
            Skill_Level skillLevelToDelete = skillLevelOptional.get();
            skillLevelRepo.delete(skillLevelToDelete);
            return true; // Deletion successful
        } else {
            throw new SkillLevelNotFoundException("Skill Level not found with ID: " + SkillLevelID);
        }
    }
}
