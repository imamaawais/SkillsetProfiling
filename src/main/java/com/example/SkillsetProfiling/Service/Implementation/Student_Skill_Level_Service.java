package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Student_Skill_Level_DTO;
import com.example.SkillsetProfiling.Entity.Student_Skill_Level;
import com.example.SkillsetProfiling.Exception.DuplicateStudentSkillLevelException;
import com.example.SkillsetProfiling.Exception.StudentSkillLevelNotFoundException;
import com.example.SkillsetProfiling.Repository.Student_Skill_Level_Repo;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Skill_Level_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Student_Skill_Level_Service implements IStudent_Skill_Level_Service {
    private Student_Skill_Level_Repo studentSkillLevelRepo;
    private ModelMapper mapper;


    @Override
    @Transactional
    public Student_Skill_Level_DTO addStudentSkillLevel(Student_Skill_Level_DTO studentSkillLevelDTO) {
        Student_Skill_Level studentSkillLevel = mapper.map(studentSkillLevelDTO, Student_Skill_Level.class);

        // Check if a student skill level with the same ID already exists
        if (studentSkillLevelRepo.findByStudentSkillLevel(studentSkillLevelDTO.getStudentDetails().getStudentID(), studentSkillLevelDTO.getSkills().getSkillID(), studentSkillLevelDTO.getSkillLevel().getLevelID()).isPresent())
            throw new DuplicateStudentSkillLevelException("Student skill level with the same ID already exists: " + studentSkillLevel.getStudentSkillLevelID());

        // Save to the database
        Student_Skill_Level savedStudentSkillLevel = studentSkillLevelRepo.save(studentSkillLevel);
        Student_Skill_Level_DTO savedStudentSkillLevelDTO = mapper.map(savedStudentSkillLevel, Student_Skill_Level_DTO.class);
        return savedStudentSkillLevelDTO;
    }

    @Override
    public Student_Skill_Level_DTO getStudentSkillLevelByStudentSkillLevelID(Integer studentSkillLevelID) {
        Optional<Student_Skill_Level> studentSkillLevelOptional = studentSkillLevelRepo.findById(studentSkillLevelID);

        if (studentSkillLevelOptional.isPresent()) {
            return mapper.map(studentSkillLevelOptional.get(), Student_Skill_Level_DTO.class);
        } else {
            throw new StudentSkillLevelNotFoundException("Student skill level not found with ID: " + studentSkillLevelID);
        }
    }

    @Override
    public List<Student_Skill_Level_DTO> getAllStudentSkillLevels() {
        List<Student_Skill_Level> allStudentSkillLevels = studentSkillLevelRepo.findAll();
        return allStudentSkillLevels.stream()
                .map(studentSkillLevel -> mapper.map(studentSkillLevel, Student_Skill_Level_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Student_Skill_Level_DTO updateStudentSkillLevel(Integer studentSkillLevelID, Student_Skill_Level_DTO updatedStudentSkillLevelDTO) {
        Optional<Student_Skill_Level> existingStudentSkillLevelOptional = studentSkillLevelRepo.findById(studentSkillLevelID);

        if (existingStudentSkillLevelOptional.isPresent()) {
            Student_Skill_Level existingStudentSkillLevel = existingStudentSkillLevelOptional.get();
            // Update student skill level based on updatedStudentSkillLevelDTO
            // Update other fields as needed

            existingStudentSkillLevel.setStudentDetails(updatedStudentSkillLevelDTO.getStudentDetails());
            existingStudentSkillLevel.setSkills(updatedStudentSkillLevelDTO.getSkills());
            existingStudentSkillLevel.setSkillLevel(updatedStudentSkillLevelDTO.getSkillLevel());
            existingStudentSkillLevel.setProgress(updatedStudentSkillLevelDTO.getProgress());

            Student_Skill_Level updatedStudentSkillLevel = studentSkillLevelRepo.save(existingStudentSkillLevel);
            return mapper.map(updatedStudentSkillLevel, Student_Skill_Level_DTO.class);
        } else {
            throw new StudentSkillLevelNotFoundException("Student skill level not found with ID: " + studentSkillLevelID);
        }
    }

    @Override
    @Transactional
    public boolean deleteStudentSkillLevel(Integer studentSkillLevelID) {
        Optional<Student_Skill_Level> studentSkillLevelOptional = studentSkillLevelRepo.findById(studentSkillLevelID);

        if (studentSkillLevelOptional.isPresent()) {
            studentSkillLevelRepo.deleteById(studentSkillLevelID);
            return true; // Deletion successful
        } else {
            throw new StudentSkillLevelNotFoundException("Student skill level not found with ID: " + studentSkillLevelID);
        }
    }

}
