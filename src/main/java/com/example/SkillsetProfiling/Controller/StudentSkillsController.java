package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Student_Skills_DTO;
import com.example.SkillsetProfiling.Exception.StudentSkillsNotFoundException;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Skills_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/student_skills")
public class StudentSkillsController {

    private final IStudent_Skills_Service service;
    @PostMapping("/add")
    public ResponseEntity<Student_Skills_DTO> addStudentSkills(@RequestBody Student_Skills_DTO studentSkillsDTO) {
        Student_Skills_DTO savedDTO = service.addStudentSkills(studentSkillsDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student_Skills_DTO>> getAllStudentSkills() {
        List<Student_Skills_DTO> studentSkillsList = service.getAllStudentSkills();

        if (studentSkillsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentSkillsList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{studentID}/{skillID}")
    public ResponseEntity<Student_Skills_DTO> getStudentSkillsById(
            @PathVariable Integer studentID,
            @PathVariable Integer skillID) {

        Student_Skills_Key key = new Student_Skills_Key(studentID, skillID);
        try {
            Student_Skills_DTO studentSkillsDTO = service.getStudentSkillsById(key);
            return new ResponseEntity<>(studentSkillsDTO, HttpStatus.OK);
        } catch (StudentSkillsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{studentID}/{skillID}")
    public ResponseEntity<Student_Skills_DTO> updateStudentSkills(
            @PathVariable Integer studentID,
            @PathVariable Integer skillID,
            @RequestBody Student_Skills_DTO updatedStudentSkillsDTO) {

        Student_Skills_Key key = new Student_Skills_Key(studentID, skillID);
        try {
            Student_Skills_DTO updatedStudentSkills = service.updateStudentSkills(key, updatedStudentSkillsDTO);
            return new ResponseEntity<>(updatedStudentSkills, HttpStatus.OK);
        } catch (StudentSkillsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{studentID}/{skillID}")
    public ResponseEntity<Void> deleteStudentSkills(
            @PathVariable Integer studentID,
            @PathVariable Integer skillID) {
        Student_Skills_Key key = new Student_Skills_Key(studentID, skillID);
        try {
            boolean deleted = service.deleteStudentSkills(key);

            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (StudentSkillsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
