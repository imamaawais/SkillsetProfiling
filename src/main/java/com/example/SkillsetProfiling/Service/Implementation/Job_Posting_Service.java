package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Job_Postings_DTO;
import com.example.SkillsetProfiling.Entity.Job_Postings;
import com.example.SkillsetProfiling.Exception.DuplicateJobPostingException;
import com.example.SkillsetProfiling.Exception.JobPostingNotFoundException;
import com.example.SkillsetProfiling.Exception.QualificationNotFoundException;
import com.example.SkillsetProfiling.Repository.Job_Postings_Repo;
import com.example.SkillsetProfiling.Service.Interface.IJob_Posting_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Job_Posting_Service implements IJob_Posting_Service {

    private Job_Postings_Repo jobPostingsRepo;
    private ModelMapper mapper;

    @Override
    public Job_Postings_DTO addJobPosting(Job_Postings_DTO jobPostingsDto) throws DuplicateJobPostingException {
        Job_Postings jobPosting = mapper.map(jobPostingsDto, Job_Postings.class);

        if(jobPostingsRepo.findById(jobPosting.getJobID()).isPresent()) {
            throw new DuplicateJobPostingException("Job with the same ID already exists: " + jobPosting.getJobID());
        }

        Job_Postings savedJobPosting = jobPostingsRepo.save(jobPosting);
        return mapper.map(savedJobPosting, Job_Postings_DTO.class);
    }

    @Override
    public Job_Postings_DTO getJobPostingByID(Integer jobPostingID) throws JobPostingNotFoundException {
        Optional<Job_Postings> jobPostingOptional = jobPostingsRepo.findById(jobPostingID);

        if (jobPostingOptional.isPresent()) {
            return mapper.map(jobPostingOptional.get(), Job_Postings_DTO.class);
        } else {
            throw new JobPostingNotFoundException("Job not found with ID: " + jobPostingID);
        }
    }

    @Override
    public List<Job_Postings_DTO> getAllJobPostings() {
        List<Job_Postings> jobPostings = jobPostingsRepo.findAll();
        return jobPostings.stream()
                .map(jobPosting -> mapper.map(jobPosting, Job_Postings_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Job_Postings_DTO updateJobPosting(Integer jobPostingID, Job_Postings_DTO updatedJobPostingDTO) throws JobPostingNotFoundException {
        Optional<Job_Postings> jobPostingOptional = jobPostingsRepo.findById(jobPostingID);

        if (jobPostingOptional.isPresent()) {
            Job_Postings existingJobPosting = jobPostingOptional.get();

            // Update the properties of the existing qualification with the values from the updatedQualificationDTO
            existingJobPosting.setJobDescription(updatedJobPostingDTO.getJobDescription());
            existingJobPosting.setJobTitle(updatedJobPostingDTO.getJobTitle());
            existingJobPosting.setPostingStatus(updatedJobPostingDTO.getPostingStatus());
            existingJobPosting.setHrDetails(updatedJobPostingDTO.getHrDetails());
            existingJobPosting.setRequiredIndustrialExperience(updatedJobPostingDTO.getRequiredIndustrialExperience());
            existingJobPosting.setTimestampClosed(updatedJobPostingDTO.getTimestampClosed());
            existingJobPosting.setTimestampCreated(updatedJobPostingDTO.getTimestampCreated());
            // Add other properties as needed

            // Save the updated qualification
            Job_Postings updatedJobPosting = jobPostingsRepo.save(existingJobPosting);

            // Map the updated qualification to Qualification_DTO and return
            return mapper.map(updatedJobPosting, Job_Postings_DTO.class);
        } else {
            throw new JobPostingNotFoundException("Job not found with ID: " + jobPostingID);
        }
    }

    @Override
    public boolean deleteJobPosting(Integer jobPostingID) {
        Optional<Job_Postings> jobPostingsOptional = jobPostingsRepo.findById(jobPostingID);

        if (jobPostingsOptional.isPresent()) {
            Job_Postings jobPostingToDelete = jobPostingsOptional.get();
            jobPostingsRepo.delete(jobPostingToDelete);
            return true; // Deletion successful
        } else {
            throw new QualificationNotFoundException("Job not found with ID: " + jobPostingID);
        }
    }
}
