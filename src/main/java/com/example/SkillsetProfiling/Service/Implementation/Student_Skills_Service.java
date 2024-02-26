package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Student_Skills_DTO;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Exception.DuplicateStudentSkillsException;
import com.example.SkillsetProfiling.Exception.StudentSkillsNotFoundException;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import com.example.SkillsetProfiling.Repository.Student_Skills_Repo;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Skills_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Student_Skills_Service implements IStudent_Skills_Service {
    private final Student_Skills_Repo studentSkillsRepo;
    private final ModelMapper mapper;

    @Override
    public Student_Skills_DTO addStudentSkills(Student_Skills_DTO studentSkillsDTO) {
        Student_Skills studentSkills = mapper.map(studentSkillsDTO, Student_Skills.class);

        Student_Skills_Key studentSkillsKey = new Student_Skills_Key();
        studentSkillsKey.setStudentID(studentSkills.getStudentID().getStudentID());
        studentSkillsKey.setSkillID(studentSkills.getSkillID().getSkillID());

        // Check if student skills with the same key already exist
        Optional<Student_Skills> existingStudentSkills = studentSkillsRepo.findById(studentSkillsKey);
        if (existingStudentSkills.isPresent()) {
            throw new DuplicateStudentSkillsException("Student skills with the same key already exist: " + studentSkillsKey);
        }

        Student_Skills savedStudentSkills = studentSkillsRepo.save(studentSkills);
        Student_Skills_DTO savedStudentSkillsDTO = mapper.map(savedStudentSkills, Student_Skills_DTO.class);
        return savedStudentSkillsDTO;
    }

    @Override
    public List<Student_Skills_DTO> getAllStudentSkills() {
        List<Student_Skills> allStudentSkills = studentSkillsRepo.findAll();
        return allStudentSkills.stream()
                .map(studentSkills -> mapper.map(studentSkills, Student_Skills_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Student_Skills_DTO getStudentSkillsById(Student_Skills_Key key) {
        Optional<Student_Skills> studentSkillsOptional = studentSkillsRepo.findById(key);

        if (studentSkillsOptional.isPresent()) {
            return mapper.map(studentSkillsOptional.get(), Student_Skills_DTO.class);
        } else {
            throw new StudentSkillsNotFoundException("Student Skills not found with ID: " + key);
        }
    }

    @Override
    public Student_Skills_DTO updateStudentSkills(Student_Skills_Key key, Student_Skills_DTO updatedStudentSkillsDTO) {
        Optional<Student_Skills> existingStudentSkillsOptional = studentSkillsRepo.findById(key);

        if (existingStudentSkillsOptional.isPresent()) {
            Student_Skills existingStudentSkills = existingStudentSkillsOptional.get();

            // Update student skills details based on updatedStudentSkillsDTO
            existingStudentSkills.setSelfLevel(updatedStudentSkillsDTO.getSelfLevel());
            existingStudentSkills.setLevelID(updatedStudentSkillsDTO.getSkill_level());

            Student_Skills updatedStudentSkills = studentSkillsRepo.save(existingStudentSkills);
            return mapper.map(updatedStudentSkills, Student_Skills_DTO.class);
        } else {
            throw new StudentSkillsNotFoundException("Student skills not found with key: " + key);
        }
    }

    @Override
    public boolean deleteStudentSkills(Student_Skills_Key key) {
        Optional<Student_Skills> studentSkillsOptional = studentSkillsRepo.findById(key);

        if (studentSkillsOptional.isPresent()) {
            studentSkillsRepo.deleteById(key);
            return true;
        } else {
            throw new StudentSkillsNotFoundException("Student skills not found with ID: " + key);
        }
    }


}
