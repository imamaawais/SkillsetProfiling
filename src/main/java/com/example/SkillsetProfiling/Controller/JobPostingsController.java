package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Job_Postings_DTO;
import com.example.SkillsetProfiling.Exception.UserNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Job_Posting_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/job_postings")
public class JobPostingsController {


    private Job_Posting_Service service;

    @PostMapping("/add")
    public ResponseEntity<Job_Postings_DTO> addJobPosting(@RequestBody Job_Postings_DTO jobPosting) {
        Job_Postings_DTO savedJobPosting = service.addJobPosting(jobPosting);
        return new ResponseEntity<>(savedJobPosting, HttpStatus.CREATED);
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Job_Postings_DTO>> getAllJobPostings() {
        List<Job_Postings_DTO> userList = service.getAllJobPostings();

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{jobPostingID}")
    public ResponseEntity<Job_Postings_DTO> getJobPostingByID(@PathVariable Integer jobPostingID) {
        Job_Postings_DTO user = service.getJobPostingByID(jobPostingID);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getFromHRId/{hrID}")
    public ResponseEntity<List<Job_Postings_DTO>> getJobPostingByHRID(@PathVariable Integer hrID) {
        List<Job_Postings_DTO> user = service.getJobPostingByHRID(hrID);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{jobPostingID}")
    public ResponseEntity<Job_Postings_DTO> updateJobPosting(@PathVariable Integer jobPostingID, @RequestBody Job_Postings_DTO updatedjobPosting) {
        Job_Postings_DTO updatedJobPosting = service.updateJobPosting(jobPostingID, updatedjobPosting);

        if (updatedJobPosting == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedJobPosting, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{jobPostingID}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Integer jobPostingID) {
       try{
           boolean deleted = service.deleteJobPosting(jobPostingID);
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
