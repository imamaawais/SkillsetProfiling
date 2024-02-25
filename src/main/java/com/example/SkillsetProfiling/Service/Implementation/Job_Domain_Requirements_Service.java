package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Job_Domain_Requirements_DTO;
import com.example.SkillsetProfiling.Entity.Job_Domain_Requirements;
import com.example.SkillsetProfiling.Exception.DuplicateJobDomainRequirementsException;
import com.example.SkillsetProfiling.Exception.JobDomainRequirementsNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;
import com.example.SkillsetProfiling.Repository.Job_Domain_Requirements_Repo;
import com.example.SkillsetProfiling.Service.Interface.IJob_Domain_Requirements_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Job_Domain_Requirements_Service implements IJob_Domain_Requirements_Service {

    private Job_Domain_Requirements_Repo jobDomainRequirementsRepo;
    private ModelMapper mapper;

    @Override
    public Job_Domain_Requirements_DTO addJobDomainRequirements(Job_Domain_Requirements_DTO jobDomainRequirementsDto) throws DuplicateJobDomainRequirementsException {
        Job_Domain_Requirements jobDomainRequirements = mapper.map(jobDomainRequirementsDto, Job_Domain_Requirements.class);
        Job_Domain_Requirements_Key jobDomainRequirementsKey = new Job_Domain_Requirements_Key(jobDomainRequirements.getJobID().getJobID(), jobDomainRequirements.getDomainID().getDomainID());

        if (jobDomainRequirementsRepo.findById(jobDomainRequirementsKey).isPresent()) {
            throw new DuplicateJobDomainRequirementsException("Job Domain Requirements with the same ID already exists: " + jobDomainRequirementsKey);
        }

        Job_Domain_Requirements savedJobDomainRequirements = jobDomainRequirementsRepo.save(jobDomainRequirements);
        return mapper.map(savedJobDomainRequirements, Job_Domain_Requirements_DTO.class);
    }

    @Override
    public Job_Domain_Requirements_DTO getJobDomainRequirementsByID(Job_Domain_Requirements_Key jobDomainRequirementsKey) throws JobDomainRequirementsNotFoundException {
        Optional<Job_Domain_Requirements> jobDomainRequirementsOptional = jobDomainRequirementsRepo.findById(jobDomainRequirementsKey);

        if (jobDomainRequirementsOptional.isPresent()) {
            return mapper.map(jobDomainRequirementsOptional.get(), Job_Domain_Requirements_DTO.class);
        } else {
            throw new JobDomainRequirementsNotFoundException("Job Domain Requirements not found with ID: " + jobDomainRequirementsKey);
        }
    }

    @Override
    public List<Job_Domain_Requirements_DTO> getAllJobDomainRequirements() {
        List<Job_Domain_Requirements> jobDomainRequirements = jobDomainRequirementsRepo.findAll();
        return jobDomainRequirements.stream()
                .map(jobDomainRequirement -> mapper.map(jobDomainRequirement, Job_Domain_Requirements_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Job_Domain_Requirements_DTO updateJobDomainRequirements(Job_Domain_Requirements_Key jobDomainRequirementsKey, Job_Domain_Requirements_DTO updatedJobDomainRequirementsDTO) throws JobDomainRequirementsNotFoundException {
        Optional<Job_Domain_Requirements> jobDomainRequirementsOptional = jobDomainRequirementsRepo.findById(jobDomainRequirementsKey);

        if (jobDomainRequirementsOptional.isPresent()) {
            Job_Domain_Requirements existingJobDomainRequirements = jobDomainRequirementsOptional.get();
            // Update properties if needed
            Job_Domain_Requirements updatedJobDomainRequirements = jobDomainRequirementsRepo.save(existingJobDomainRequirements);
            return mapper.map(updatedJobDomainRequirements, Job_Domain_Requirements_DTO.class);
        } else {
            throw new JobDomainRequirementsNotFoundException("Job Domain Requirements not found with ID: " + jobDomainRequirementsKey);
        }
    }

    @Override
    public boolean deleteJobDomainRequirements(Job_Domain_Requirements_Key jobDomainRequirementsKey) {
        Optional<Job_Domain_Requirements> jobDomainRequirementsOptional = jobDomainRequirementsRepo.findById(jobDomainRequirementsKey);

        if (jobDomainRequirementsOptional.isPresent()) {
            Job_Domain_Requirements jobDomainRequirementsToDelete = jobDomainRequirementsOptional.get();
            jobDomainRequirementsRepo.delete(jobDomainRequirementsToDelete);
            return true; // Deletion successful
        } else {
            return false; // Deletion unsuccessful
        }
    }
}
