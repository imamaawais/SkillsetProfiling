package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Job_Domain_Requirements_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateJobDomainRequirementsException;
import com.example.SkillsetProfiling.Exception.JobDomainRequirementsNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;
import com.example.SkillsetProfiling.Service.Implementation.Job_Domain_Requirements_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/job_domain_requirements")
public class JobDomainRequirementsController {

    private final Job_Domain_Requirements_Service service;

    @PostMapping("/add")
    public ResponseEntity<Job_Domain_Requirements_DTO> addJobDomainRequirements(@RequestBody Job_Domain_Requirements_DTO jobDomainRequirements) {
        try {
            Job_Domain_Requirements_DTO savedJobDomainRequirements = service.addJobDomainRequirements(jobDomainRequirements);
            return new ResponseEntity<>(savedJobDomainRequirements, HttpStatus.CREATED);
        } catch (DuplicateJobDomainRequirementsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getFromId/{jobID}/{domainID}")
    public ResponseEntity<Job_Domain_Requirements_DTO> getJobDomainRequirementsByID(@PathVariable Integer jobID, @PathVariable Integer domainID) {
        Job_Domain_Requirements_Key jobDomainRequirementsKey = new Job_Domain_Requirements_Key(jobID, domainID);
        try {
            Job_Domain_Requirements_DTO jobDomainRequirements = service.getJobDomainRequirementsByID(jobDomainRequirementsKey);
            return new ResponseEntity<>(jobDomainRequirements, HttpStatus.OK);
        } catch (JobDomainRequirementsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Job_Domain_Requirements_DTO>> getAllJobDomainRequirements() {
        List<Job_Domain_Requirements_DTO> jobDomainRequirementsList = service.getAllJobDomainRequirements();
        if (jobDomainRequirementsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jobDomainRequirementsList, HttpStatus.OK);
    }

    @PutMapping("/update/{jobID}/{domainID}")
    public ResponseEntity<Job_Domain_Requirements_DTO> updateJobDomainRequirements(@PathVariable Integer jobID, @PathVariable Integer domainID, @RequestBody Job_Domain_Requirements_DTO updatedJobDomainRequirements) {
        Job_Domain_Requirements_Key jobDomainRequirementsKey = new Job_Domain_Requirements_Key(jobID, domainID);
        try {
            Job_Domain_Requirements_DTO updatedJobDomainRequirementsResult = service.updateJobDomainRequirements(jobDomainRequirementsKey, updatedJobDomainRequirements);
            return new ResponseEntity<>(updatedJobDomainRequirementsResult, HttpStatus.OK);
        } catch (JobDomainRequirementsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{jobID}/{domainID}")
    public ResponseEntity<Void> deleteJobDomainRequirements(@PathVariable Integer jobID, @PathVariable Integer domainID) {
        Job_Domain_Requirements_Key jobDomainRequirementsKey = new Job_Domain_Requirements_Key(jobID, domainID);
        boolean deleted = service.deleteJobDomainRequirements(jobDomainRequirementsKey);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
