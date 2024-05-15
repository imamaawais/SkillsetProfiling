package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Job_Eligibility_Check_DTO;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;
import com.example.SkillsetProfiling.Key.Job_Eligibility_Check_Key;
import com.example.SkillsetProfiling.Service.Implementation.Job_Eligibility_Check_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/job_eligibility_check")
public class JobEligibilityCheckController {


    private Job_Eligibility_Check_Service service;

    @PostMapping("/add")
    public ResponseEntity<Job_Eligibility_Check_DTO> addJobEligibilityCheck(@RequestBody Job_Eligibility_Check_DTO jobEligibilityCheck) {
        Job_Eligibility_Check_DTO savedJobEligibilityCheck = service.addJobEligibilityCheck(jobEligibilityCheck);
        return new ResponseEntity<>(savedJobEligibilityCheck, HttpStatus.CREATED);
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Job_Eligibility_Check_DTO>> getAllJobEligibilityChecks() {
        List<Job_Eligibility_Check_DTO> userList = service.getAllJobEligibilityChecks();

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getFromJobId/{jobPostingID}")
    public ResponseEntity<List<Job_Eligibility_Check_DTO>> getJobEligibilityChecksByJobID(@PathVariable Integer jobPostingID) {
        List<Job_Eligibility_Check_DTO> userList = service.getJobEligibilityChecksByJobID(jobPostingID);

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{jobPostingID}/{studentID}")
    public ResponseEntity<Job_Eligibility_Check_DTO> getJobEligibilityCheckByID(@PathVariable Integer jobPostingID, @PathVariable Integer studentID) {
        Job_Eligibility_Check_Key jobEligibilityCheckKey = new Job_Eligibility_Check_Key(jobPostingID, studentID);
        Job_Eligibility_Check_DTO user = service.getJobEligibilityCheckByID(jobEligibilityCheckKey);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{jobPostingID}/{studentID}")
    public ResponseEntity<Job_Eligibility_Check_DTO> updateJobEligibilityCheck(@PathVariable Integer jobPostingID, @PathVariable Integer studentID, @RequestBody Job_Eligibility_Check_DTO updatedjobEligibilityCheck) {
        Job_Eligibility_Check_Key jobEligibilityCheckKey = new Job_Eligibility_Check_Key(jobPostingID, studentID);
        Job_Eligibility_Check_DTO updatedJobEligibilityCheck = service.updateJobEligibilityCheck(jobEligibilityCheckKey, updatedjobEligibilityCheck);

        if (updatedJobEligibilityCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedJobEligibilityCheck, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{jobPostingID}/{studentID}")
    public ResponseEntity<Void> deleteJobEligibilityCheck(@PathVariable Integer jobPostingID, @PathVariable Integer studentID) {

        Job_Eligibility_Check_Key jobEligibilityCheckKey = new Job_Eligibility_Check_Key(jobPostingID, studentID);
       try{
           boolean deleted = service.deleteJobEligibilityCheck(jobEligibilityCheckKey);
           if (deleted){
               return new ResponseEntity<>(HttpStatus.OK);
           }else{
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
       } catch(UserNotFoundException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
         }
}
