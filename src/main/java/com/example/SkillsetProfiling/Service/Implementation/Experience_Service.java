package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Experience_DTO;
import com.example.SkillsetProfiling.Entity.Experience;
import com.example.SkillsetProfiling.Exception.DuplicateExperienceException;
import com.example.SkillsetProfiling.Exception.ExperienceNotFoundException;
import com.example.SkillsetProfiling.Repository.Experience_Repo;
import com.example.SkillsetProfiling.Service.Interface.IExperience_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Experience_Service implements IExperience_Service {

    private Experience_Repo experienceRepo;
    private ModelMapper mapper;

    @Override
    public Experience_DTO addExperience(Experience_DTO experienceDTO) {
        // Map the ExperienceDTO to the Experience entity
        Experience experience = mapper.map(experienceDTO, Experience.class);



        // No experience with the same ID, proceed to save the new experience
        Experience savedExperience = experienceRepo.save(experience);
        Experience_DTO savedExperienceDTO = mapper.map(savedExperience, Experience_DTO.class);
        // Map the saved Experience entity back to ExperienceDTO
        return savedExperienceDTO;
    }


    @Override
    public List<Experience_DTO> getAllExperiences() {
        List<Experience> experiences = experienceRepo.findAll();
        return experiences.stream()
                .map(experience -> mapper.map(experience, Experience_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Experience_DTO getExperienceByID(Integer experienceID) throws ExperienceNotFoundException {
        Optional<Experience> experienceOptional = experienceRepo.findById(experienceID);

        if (experienceOptional.isPresent()) {
            return mapper.map(experienceOptional.get(), Experience_DTO.class);
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceID);
        }

    }

    @Override
    public Experience_DTO updateExperience(Integer experienceID, Experience_DTO updatedExperienceDTO) throws ExperienceNotFoundException {
        Optional<Experience> experienceOptional = experienceRepo.findById(experienceID);

        if (experienceOptional.isPresent()) {
            Experience existingExperience = experienceOptional.get();

            // Update the properties of the existing experience with the values from the updatedExperienceDTO
            existingExperience.setExperienceName(updatedExperienceDTO.getExperienceName());
            // Add other properties as needed

            // Save the updated experience
            Experience updatedExperience = experienceRepo.save(existingExperience);

            // Map the updated experience to Experience_DTO and return
            return mapper.map(updatedExperience, Experience_DTO.class);
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceID);
        }
    }

    @Override
    @Transactional
    public boolean deleteExperience(Integer experienceID) throws ExperienceNotFoundException {
        Optional<Experience> experienceOptional = experienceRepo.findById(experienceID);

        if (experienceOptional.isPresent()) {
            Experience experienceToDelete = experienceOptional.get();
            experienceRepo.delete(experienceToDelete);
            return true; // Deletion successful
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceID);
        }
    }
}
