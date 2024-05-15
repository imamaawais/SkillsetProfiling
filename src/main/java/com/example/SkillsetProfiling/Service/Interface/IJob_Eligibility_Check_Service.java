package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Job_Eligibility_Check_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateJobEligibilityCheckException;
import com.example.SkillsetProfiling.Exception.JobEligibilityCheckNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Eligibility_Check_Key;

import java.util.List;

public interface IJob_Eligibility_Check_Service {
    Job_Eligibility_Check_DTO addJobEligibilityCheck (Job_Eligibility_Check_DTO jobEligibilityChecksDto) throws DuplicateJobEligibilityCheckException;
    Job_Eligibility_Check_DTO getJobEligibilityCheckByID (Job_Eligibility_Check_Key jobEligibilityCheckKey) throws JobEligibilityCheckNotFoundException;
    List<Job_Eligibility_Check_DTO> getAllJobEligibilityChecks ();
    List<Job_Eligibility_Check_DTO> getJobEligibilityChecksByJobID(Integer jobID);
    Job_Eligibility_Check_DTO updateJobEligibilityCheck (Job_Eligibility_Check_Key jobEligibilityCheckKey, Job_Eligibility_Check_DTO updatedJobEligibilityCheckDTO) throws JobEligibilityCheckNotFoundException;
    boolean deleteJobEligibilityCheck (Job_Eligibility_Check_Key jobEligibilityCheckKey);
}
