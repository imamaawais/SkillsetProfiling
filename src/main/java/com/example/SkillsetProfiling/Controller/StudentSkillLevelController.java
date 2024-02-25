package com.example.SkillsetProfiling.Controller;


import com.example.SkillsetProfiling.Dto.Student_Skill_Level_DTO;
import com.example.SkillsetProfiling.Exception.StudentSkillLevelNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Student_Skill_Level_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/student_skill_level")
public class StudentSkillLevelController {
    private Student_Skill_Level_Service service;


    @PostMapping("/add")
    public ResponseEntity<Student_Skill_Level_DTO> addStudentSkillLevel(@RequestBody Student_Skill_Level_DTO studentSkillLevelDTO) {
        Student_Skill_Level_DTO addedStudentSkillLevel = service.addStudentSkillLevel(studentSkillLevelDTO);
        return new ResponseEntity<>(addedStudentSkillLevel, HttpStatus.CREATED);
    }

    @GetMapping("/getFromId/{studentSkillLevelID}")
    public ResponseEntity<Student_Skill_Level_DTO> getStudentSkillLevelByStudentSkillLevelID(@PathVariable Integer studentSkillLevelID) {
        try {
            Student_Skill_Level_DTO studentSkillLevelDTO = service.getStudentSkillLevelByStudentSkillLevelID(studentSkillLevelID);
            return new ResponseEntity<>(studentSkillLevelDTO, HttpStatus.OK);
        } catch (StudentSkillLevelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student_Skill_Level_DTO>> getAllStudentSkillLevels() {
        List<Student_Skill_Level_DTO> studentSkillLevelList = service.getAllStudentSkillLevels();

        if (studentSkillLevelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentSkillLevelList, HttpStatus.OK);
    }

    @PutMapping("/update/{studentSkillLevelID}")
    public ResponseEntity<Student_Skill_Level_DTO> updateStudentSkillLevel(@PathVariable Integer studentSkillLevelID,
                                                                           @RequestBody Student_Skill_Level_DTO updatedStudentSkillLevelDTO) {
        try {
            Student_Skill_Level_DTO updatedStudentSkillLevel = service.updateStudentSkillLevel(studentSkillLevelID, updatedStudentSkillLevelDTO);
            return new ResponseEntity<>(updatedStudentSkillLevel, HttpStatus.OK);
        } catch (StudentSkillLevelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{studentSkillLevelID}")
    public ResponseEntity<Void> deleteStudentSkillLevel(@PathVariable Integer studentSkillLevelID) {
        try {
            boolean deleted = service.deleteStudentSkillLevel(studentSkillLevelID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (StudentSkillLevelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
