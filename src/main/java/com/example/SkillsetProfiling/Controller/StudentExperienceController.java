package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Student_Experience_DTO;
import com.example.SkillsetProfiling.Exception.StudentExperienceNotFoundException;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;
import com.example.SkillsetProfiling.Service.Implementation.Student_Experience_Service;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Experience_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/student_experience")
public class StudentExperienceController {

    private final Student_Experience_Service service;

    @PostMapping("/add")
    public ResponseEntity<Student_Experience_DTO> addStudentExperience(@RequestBody Student_Experience_DTO studentExperienceDTO) {
        try {
            Student_Experience_DTO addedStudentExperience = service.addStudentExperience(studentExperienceDTO);
            return new ResponseEntity<>(addedStudentExperience, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student_Experience_DTO>> getAllStudentExperiences() {
        List<Student_Experience_DTO> studentExperiences = service.getAllStudentExperiences();
        if (studentExperiences.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentExperiences, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{studentID}/{experienceID}")
    public ResponseEntity<Student_Experience_DTO> getStudentExperienceByID(@PathVariable Integer studentID, @PathVariable Integer experienceID) {
        Student_Experience_Key studentExperienceKey = new Student_Experience_Key(studentID, experienceID);
        try {
            Student_Experience_DTO studentExperienceDTO = service.getStudentExperienceByID(studentExperienceKey);
            return new ResponseEntity<>(studentExperienceDTO, HttpStatus.OK);
        } catch (StudentExperienceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{studentID}/{experienceID}")
    public ResponseEntity<Student_Experience_DTO> updateStudentExperience(@PathVariable Integer studentID, @PathVariable Integer experienceID, @RequestBody Student_Experience_DTO updatedStudentExperienceDTO) {
        Student_Experience_Key studentExperienceKey = new Student_Experience_Key(studentID, experienceID);
        try {
            Student_Experience_DTO updatedStudentExperience = service.updateStudentExperience(studentExperienceKey, updatedStudentExperienceDTO);
            return new ResponseEntity<>(updatedStudentExperience, HttpStatus.OK);
        } catch (StudentExperienceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{studentID}/{experienceID}")
    public ResponseEntity<Void> deleteStudentExperience(@PathVariable Integer studentID, @PathVariable Integer experienceID) {
        Student_Experience_Key studentExperienceKey = new Student_Experience_Key(studentID, experienceID);
        try {
            boolean deleted = service.deleteStudentExperience(studentExperienceKey);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (StudentExperienceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
