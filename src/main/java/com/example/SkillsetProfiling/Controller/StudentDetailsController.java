package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Student_Details_DTO;
import com.example.SkillsetProfiling.Exception.StudentDetailsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Student_Details_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/student_details")
public class StudentDetailsController {

    private Student_Details_Service service;

    @PostMapping
    public ResponseEntity<Student_Details_DTO> addStudentDetails(@RequestBody Student_Details_DTO studentDetailsDTO) {
        Student_Details_DTO addedStudentDetails = service.addStudentDetails(studentDetailsDTO);
        return new ResponseEntity<>(addedStudentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{studentDetailsID}")
    public ResponseEntity<Student_Details_DTO> getStudentDetailsByStudentID(@PathVariable Integer studentDetailsID) {
        try {
            Student_Details_DTO studentDetailsDTO = service.getStudentDetailsByStudentID(studentDetailsID);
            return new ResponseEntity<>(studentDetailsDTO, HttpStatus.OK);
        } catch (StudentDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student_Details_DTO>> getAllStudentDetails() {
        List<Student_Details_DTO> studentDetailsList = service.getAllStudentDetails();

        if (studentDetailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentDetailsList, HttpStatus.OK);
    }

    @PutMapping("/{studentID}")
    public ResponseEntity<Student_Details_DTO> updateStudentDetails(@PathVariable Integer studentID,
                                                                    @RequestBody Student_Details_DTO updatedStudentDetailsDTO) {
        try {
            Student_Details_DTO updatedStudentDetails = service.updateStudentDetails(studentID, updatedStudentDetailsDTO);
            return new ResponseEntity<>(updatedStudentDetails, HttpStatus.OK);
        } catch (StudentDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{studentID}")
    public ResponseEntity<Void> deleteStudentDetails(@PathVariable Integer studentID) {
        try {
            boolean deleted = service.deleteStudentDetails(studentID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (StudentDetailsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
