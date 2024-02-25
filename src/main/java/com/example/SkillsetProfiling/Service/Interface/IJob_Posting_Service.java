package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Job_Postings_DTO;
import com.example.SkillsetProfiling.Service.Implementation.DuplicateJobPostingException;
import com.example.SkillsetProfiling.Service.Implementation.JobPostingNotFoundException;

import java.util.List;

public interface IJob_Posting_Service {
    Job_Postings_DTO addJobPosting (Job_Postings_DTO jobPostingsDto) throws DuplicateJobPostingException;
    Job_Postings_DTO getJobPostingByID (Integer jobPostingID) throws JobPostingNotFoundException;
    List<Job_Postings_DTO> getAllJobPostings ();
    Job_Postings_DTO updateJobPosting (Integer jobPostingID, Job_Postings_DTO updatedJobPostingDTO) throws JobPostingNotFoundException;
    boolean deleteJobPosting (Integer jobPostingID);
}
