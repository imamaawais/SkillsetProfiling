package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Job_Domain_Requirements_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateJobDomainRequirementsException;
import com.example.SkillsetProfiling.Exception.JobDomainRequirementsNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;

import java.util.List;

public interface IJob_Domain_Requirements_Service {
    Job_Domain_Requirements_DTO addJobDomainRequirements(Job_Domain_Requirements_DTO jobDomainRequirementsDto) throws DuplicateJobDomainRequirementsException;
    Job_Domain_Requirements_DTO getJobDomainRequirementsByID(Job_Domain_Requirements_Key jobDomainRequirementsKey) throws JobDomainRequirementsNotFoundException;
    List<Job_Domain_Requirements_DTO> getAllJobDomainRequirements();
    Job_Domain_Requirements_DTO updateJobDomainRequirements(Job_Domain_Requirements_Key jobDomainRequirementsKey, Job_Domain_Requirements_DTO updatedJobDomainRequirementsDTO) throws JobDomainRequirementsNotFoundException;
    boolean deleteJobDomainRequirements(Job_Domain_Requirements_Key jobDomainRequirementsKey);
}
