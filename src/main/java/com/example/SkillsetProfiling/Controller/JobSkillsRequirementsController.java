package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Job_Skills_Requirements_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateJobSkillsRequirementsException;
import com.example.SkillsetProfiling.Exception.JobSkillsRequirementsNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Skills_Requirements_Key;
import com.example.SkillsetProfiling.Service.Implementation.Job_Skills_Requirements_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/job_skills_requirements")
public class JobSkillsRequirementsController {

    private final Job_Skills_Requirements_Service service;

    @PostMapping("/add")
    public ResponseEntity<Job_Skills_Requirements_DTO> addJobSkillsRequirements(@RequestBody Job_Skills_Requirements_DTO jobSkillsRequirements) {
        try {
            Job_Skills_Requirements_DTO savedJobSkillsRequirements = service.addJobSkillsRequirements(jobSkillsRequirements);
            return new ResponseEntity<>(savedJobSkillsRequirements, HttpStatus.CREATED);
        } catch (DuplicateJobSkillsRequirementsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/{jobID}/{skillID}/{levelID}")
    public ResponseEntity<Job_Skills_Requirements_DTO> getJobSkillsRequirementsByID(@PathVariable Integer jobID, @PathVariable Integer skillID, @PathVariable Integer levelID) {
        Job_Skills_Requirements_Key jobSkillsRequirementsKey = new Job_Skills_Requirements_Key(jobID, skillID, levelID);
        try {
            Job_Skills_Requirements_DTO jobSkillsRequirements = service.getJobSkillsRequirementsByID(jobSkillsRequirementsKey);
            return new ResponseEntity<>(jobSkillsRequirements, HttpStatus.OK);
        } catch (JobSkillsRequirementsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Job_Skills_Requirements_DTO>> getAllJobSkillsRequirements() {
        List<Job_Skills_Requirements_DTO> jobSkillsRequirementsList = service.getAllJobSkillsRequirements();
        if (jobSkillsRequirementsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jobSkillsRequirementsList, HttpStatus.OK);
    }

    @PutMapping("/update/{jobID}/{skillID}/{levelID}")
    public ResponseEntity<Job_Skills_Requirements_DTO> updateJobSkillsRequirements(@PathVariable Integer jobID, @PathVariable Integer skillID, @PathVariable Integer levelID, @RequestBody Job_Skills_Requirements_DTO updatedJobSkillsRequirements) {
        Job_Skills_Requirements_Key jobSkillsRequirementsKey = new Job_Skills_Requirements_Key(jobID, skillID, levelID);
        try {
            Job_Skills_Requirements_DTO updatedJobSkillsRequirementsResult = service.updateJobSkillsRequirements(jobSkillsRequirementsKey, updatedJobSkillsRequirements);
            return new ResponseEntity<>(updatedJobSkillsRequirementsResult, HttpStatus.OK);
        } catch (JobSkillsRequirementsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{jobID}/{skillID}/{levelID}")
    public ResponseEntity<Void> deleteJobSkillsRequirements(@PathVariable Integer jobID, @PathVariable Integer skillID, @PathVariable Integer levelID) {
        Job_Skills_Requirements_Key jobSkillsRequirementsKey = new Job_Skills_Requirements_Key(jobID, skillID, levelID);
        boolean deleted = service.deleteJobSkillsRequirements(jobSkillsRequirementsKey);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
