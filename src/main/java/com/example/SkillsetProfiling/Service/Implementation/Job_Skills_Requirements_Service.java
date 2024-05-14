package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Job_Skills_Requirements_DTO;
import com.example.SkillsetProfiling.Entity.Job_Skills_Requirements;
import com.example.SkillsetProfiling.Exception.DuplicateJobSkillsRequirementsException;
import com.example.SkillsetProfiling.Exception.JobSkillsRequirementsNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Skills_Requirements_Key;
import com.example.SkillsetProfiling.Repository.Job_Skills_Requirements_Repo;
import com.example.SkillsetProfiling.Service.Interface.IJob_Skills_Requirements_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Job_Skills_Requirements_Service implements IJob_Skills_Requirements_Service {

    private Job_Skills_Requirements_Repo jobSkillsRequirementsRepo;
//    private ModelMapper mapper;

    @Override
    public Job_Skills_Requirements_DTO addJobSkillsRequirements(Job_Skills_Requirements_DTO jobSkillsRequirementsDto) throws DuplicateJobSkillsRequirementsException {
        Job_Skills_Requirements jobSkillsRequirements = new Job_Skills_Requirements(jobSkillsRequirementsDto.getJobPostings(), jobSkillsRequirementsDto.getSkills(), jobSkillsRequirementsDto.getSkillLevel());
        Job_Skills_Requirements_Key jobSkillsRequirementsKey = new Job_Skills_Requirements_Key(jobSkillsRequirements.getJobID().getJobID(), jobSkillsRequirements.getSkillID().getSkillID());

        if (jobSkillsRequirementsRepo.findById(jobSkillsRequirementsKey).isPresent()) {
            throw new DuplicateJobSkillsRequirementsException("Job Skills Requirements with the same ID already exists: " + jobSkillsRequirementsKey);
        }

        Job_Skills_Requirements savedJobSkillsRequirements = jobSkillsRequirementsRepo.save(jobSkillsRequirements);
        return new Job_Skills_Requirements_DTO(savedJobSkillsRequirements.getJobID(), savedJobSkillsRequirements.getSkillID(), savedJobSkillsRequirements.getLevelID());
    }

    @Override
    public Job_Skills_Requirements_DTO getJobSkillsRequirementsByID(Job_Skills_Requirements_Key jobSkillsRequirementsKey) throws JobSkillsRequirementsNotFoundException {
        Optional<Job_Skills_Requirements> jobSkillsRequirementsOptional = jobSkillsRequirementsRepo.findById(jobSkillsRequirementsKey);

        if (jobSkillsRequirementsOptional.isPresent()) {
            return new Job_Skills_Requirements_DTO(jobSkillsRequirementsOptional.get().getJobID(), jobSkillsRequirementsOptional.get().getSkillID(), jobSkillsRequirementsOptional.get().getLevelID());
        } else {
            throw new JobSkillsRequirementsNotFoundException("Job Skills Requirements not found with ID: " + jobSkillsRequirementsKey);
        }
    }

    @Transactional
    @Override
    public List<Job_Skills_Requirements_DTO> getJobSkillsRequirementsByJobID(Integer jobID) {
        List<Job_Skills_Requirements> jobSkillsRequirements = jobSkillsRequirementsRepo.getJobSkillsRequirementsByJobID(jobID);
        return jobSkillsRequirements.stream()
                .map(jobSkillsRequirement -> new Job_Skills_Requirements_DTO(jobSkillsRequirement.getJobID(), jobSkillsRequirement.getSkillID(), jobSkillsRequirement.getLevelID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Job_Skills_Requirements_DTO> getAllJobSkillsRequirements() {
        List<Job_Skills_Requirements> jobSkillsRequirements = jobSkillsRequirementsRepo.findAll();
        return jobSkillsRequirements.stream()
                .map(jobSkillsRequirement -> new Job_Skills_Requirements_DTO(jobSkillsRequirement.getJobID(), jobSkillsRequirement.getSkillID(), jobSkillsRequirement.getLevelID()))
                .collect(Collectors.toList());
    }

    @Override
    public Job_Skills_Requirements_DTO updateJobSkillsRequirements(Job_Skills_Requirements_Key jobSkillsRequirementsKey, Job_Skills_Requirements_DTO updatedJobSkillsRequirementsDTO) throws JobSkillsRequirementsNotFoundException {
        Optional<Job_Skills_Requirements> jobSkillsRequirementsOptional = jobSkillsRequirementsRepo.findById(jobSkillsRequirementsKey);

        if (jobSkillsRequirementsOptional.isPresent()) {
            Job_Skills_Requirements existingJobSkillsRequirements = jobSkillsRequirementsOptional.get();
            // Update properties if needed
            existingJobSkillsRequirements.setLevelID(updatedJobSkillsRequirementsDTO.getSkillLevel());
            Job_Skills_Requirements updatedJobSkillsRequirements = jobSkillsRequirementsRepo.save(existingJobSkillsRequirements);
            return new Job_Skills_Requirements_DTO(updatedJobSkillsRequirements.getJobID(), updatedJobSkillsRequirements.getSkillID(), updatedJobSkillsRequirements.getLevelID());
        } else {
            throw new JobSkillsRequirementsNotFoundException("Job Skills Requirements not found with ID: " + jobSkillsRequirementsKey);
        }
    }

    @Override
    public boolean deleteJobSkillsRequirements(Job_Skills_Requirements_Key jobSkillsRequirementsKey) {
        Optional<Job_Skills_Requirements> jobSkillsRequirementsOptional = jobSkillsRequirementsRepo.findById(jobSkillsRequirementsKey);

        if (jobSkillsRequirementsOptional.isPresent()) {
            Job_Skills_Requirements jobSkillsRequirementsToDelete = jobSkillsRequirementsOptional.get();
            jobSkillsRequirementsRepo.delete(jobSkillsRequirementsToDelete);
            return true; // Deletion successful
        } else {
            return false; // Deletion unsuccessful
        }
    }
}
