package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Job_Eligibility_Check_DTO;
import com.example.SkillsetProfiling.Entity.Job_Eligibility_Check;
import com.example.SkillsetProfiling.Exception.DuplicateJobEligibilityCheckException;
import com.example.SkillsetProfiling.Exception.JobEligibilityCheckNotFoundException;
import com.example.SkillsetProfiling.Exception.QualificationNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Eligibility_Check_Key;
import com.example.SkillsetProfiling.Repository.Job_Eligibility_Check_Repo;
import com.example.SkillsetProfiling.Service.Interface.IJob_Eligibility_Check_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Job_Eligibility_Check_Service implements IJob_Eligibility_Check_Service {

    private Job_Eligibility_Check_Repo jobEligibilityChecksRepo;
    private ModelMapper mapper;

    @Override
    public Job_Eligibility_Check_DTO addJobEligibilityCheck(Job_Eligibility_Check_DTO jobEligibilityCheckDto) throws DuplicateJobEligibilityCheckException {
        Job_Eligibility_Check jobEligibilityCheck = mapper.map(jobEligibilityCheckDto, Job_Eligibility_Check.class);

        Job_Eligibility_Check_Key jobEligibilityCheckKey = new Job_Eligibility_Check_Key();
        
        jobEligibilityCheckKey.setJobID(jobEligibilityCheckDto.getJobPostings().getJobID());
        jobEligibilityCheckKey.setStudentID(jobEligibilityCheckDto.getStudentDetails().getStudentID());

        if(jobEligibilityChecksRepo.findById(jobEligibilityCheckKey).isPresent()) {
            throw new DuplicateJobEligibilityCheckException("Job Eligibility Check with the same ID already exists: " + jobEligibilityCheck.getJobID());
        }

        Job_Eligibility_Check savedJobEligibilityCheck = jobEligibilityChecksRepo.save(jobEligibilityCheck);
        return mapper.map(savedJobEligibilityCheck, Job_Eligibility_Check_DTO.class);
    }

    @Override
    public Job_Eligibility_Check_DTO getJobEligibilityCheckByID(Job_Eligibility_Check_Key jobEligibilityCheckKey) throws JobEligibilityCheckNotFoundException {
        Optional<Job_Eligibility_Check> jobEligibilityCheckOptional = jobEligibilityChecksRepo.findById(jobEligibilityCheckKey);

        if (jobEligibilityCheckOptional.isPresent()) {
            return mapper.map(jobEligibilityCheckOptional.get(), Job_Eligibility_Check_DTO.class);
        } else {
            throw new JobEligibilityCheckNotFoundException("Job Eligibility Check not found with ID: " + jobEligibilityCheckKey);
        }
    }

    @Override
    public List<Job_Eligibility_Check_DTO> getAllJobEligibilityChecks() {
        List<Job_Eligibility_Check> jobEligibilityChecks = jobEligibilityChecksRepo.findAll();
        return jobEligibilityChecks.stream()
                .map(jobEligibilityCheck -> mapper.map(jobEligibilityCheck, Job_Eligibility_Check_DTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Job_Eligibility_Check_DTO> getJobEligibilityChecksByJobID(Integer jobID) {
        List<Job_Eligibility_Check> jobEligibilityChecks = jobEligibilityChecksRepo.getJobEligibilityChecksByJobID(jobID);
        return jobEligibilityChecks.stream()
                .map(jobEligibilityCheck -> mapper.map(jobEligibilityCheck, Job_Eligibility_Check_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Job_Eligibility_Check_DTO updateJobEligibilityCheck(Job_Eligibility_Check_Key jobEligibilityCheckKey, Job_Eligibility_Check_DTO updatedJobEligibilityCheckDTO) throws JobEligibilityCheckNotFoundException {
        Optional<Job_Eligibility_Check> jobEligibilityCheckOptional = jobEligibilityChecksRepo.findById(jobEligibilityCheckKey);

        if (jobEligibilityCheckOptional.isPresent()) {
            Job_Eligibility_Check existingJobEligibilityCheck = jobEligibilityCheckOptional.get();

            existingJobEligibilityCheck.setEligible(updatedJobEligibilityCheckDTO.getEligible());
            existingJobEligibilityCheck.setTimestampChecked(updatedJobEligibilityCheckDTO.getTimestampChecked());
//            existingJobEligibilityCheck.setJobID(updatedJobEligibilityCheckDTO.getJobPostings());
//            existingJobEligibilityCheck.setStudentID(updatedJobEligibilityCheckDTO.getStudentDetails());
            existingJobEligibilityCheck.setApplied(updatedJobEligibilityCheckDTO.getApplied());
            existingJobEligibilityCheck.setTimestampApplied(updatedJobEligibilityCheckDTO.getTimestampApplied());

            Job_Eligibility_Check updatedJobEligibilityCheck = jobEligibilityChecksRepo.save(existingJobEligibilityCheck);

            return mapper.map(updatedJobEligibilityCheck, Job_Eligibility_Check_DTO.class);
        } else {
            throw new JobEligibilityCheckNotFoundException("Job Eligibility Check not found with ID: " + jobEligibilityCheckKey);
        }
    }

    @Override
    public boolean deleteJobEligibilityCheck(Job_Eligibility_Check_Key jobEligibilityCheckKey) {
        Optional<Job_Eligibility_Check> jobEligibilityChecksOptional = jobEligibilityChecksRepo.findById(jobEligibilityCheckKey);

        if (jobEligibilityChecksOptional.isPresent()) {
            Job_Eligibility_Check jobEligibilityCheckToDelete = jobEligibilityChecksOptional.get();
            jobEligibilityChecksRepo.delete(jobEligibilityCheckToDelete);
            return true; // Deletion successful
        } else {
            throw new QualificationNotFoundException("Job Eligibility Check not found with ID: " + jobEligibilityCheckKey);
        }
    }
}
