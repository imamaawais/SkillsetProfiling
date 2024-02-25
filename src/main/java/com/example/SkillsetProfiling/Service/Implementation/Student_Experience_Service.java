package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Student_Experience_DTO;
import com.example.SkillsetProfiling.Entity.Student_Experience;
import com.example.SkillsetProfiling.Exception.DuplicateStudentExperienceException;
import com.example.SkillsetProfiling.Exception.StudentExperienceNotFoundException;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;
import com.example.SkillsetProfiling.Repository.Student_Experience_Repo;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Experience_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Student_Experience_Service implements IStudent_Experience_Service {

    private final Student_Experience_Repo studentExperienceRepo;
    private final ModelMapper mapper;

    @Override
    public Student_Experience_DTO addStudentExperience(Student_Experience_DTO studentExperienceDTO) throws DuplicateStudentExperienceException {
        Student_Experience studentExperience = mapper.map(studentExperienceDTO, Student_Experience.class);

        Student_Experience_Key studentExperienceKey = new Student_Experience_Key();
        studentExperienceKey.setStudentID(studentExperienceDTO.getStudentDetails().getStudentID());
        studentExperienceKey.setExperienceID(studentExperienceDTO.getExperience().getExperienceID());

        if (studentExperienceRepo.findById(studentExperienceKey).isPresent()) {
            throw new DuplicateStudentExperienceException("Student Experience with the same ID already exists");
        }

        Student_Experience savedStudentExperience = studentExperienceRepo.save(studentExperience);
        return mapper.map(savedStudentExperience, Student_Experience_DTO.class);
    }

    @Override
    public List<Student_Experience_DTO> getAllStudentExperiences() {
        List<Student_Experience> studentExperiences = studentExperienceRepo.findAll();
        return studentExperiences.stream()
                .map(studentExperience -> mapper.map(studentExperience, Student_Experience_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Student_Experience_DTO getStudentExperienceByID(Student_Experience_Key studentExperienceKey) throws StudentExperienceNotFoundException {
        Optional<Student_Experience> studentExperienceOptional = studentExperienceRepo.findById(studentExperienceKey);

        if (studentExperienceOptional.isPresent()) {
            return mapper.map(studentExperienceOptional.get(), Student_Experience_DTO.class);
        } else {
            throw new StudentExperienceNotFoundException("Student Experience not found with ID: " + studentExperienceKey);
        }
    }

    @Override
    public List<Student_Experience_DTO> getStudentExperiencesByStudentID(Integer studentID) {
        List<Student_Experience> studentExperiences = studentExperienceRepo.findByStudentID_StudentID(studentID);
        return studentExperiences.stream()
                .map(studentExperience -> mapper.map(studentExperience, Student_Experience_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student_Experience_DTO> getStudentExperiencesByExperienceID(Integer experienceID) {
        List<Student_Experience> studentExperiences = studentExperienceRepo.findByExperienceID_ExperienceID(experienceID);
        return studentExperiences.stream()
                .map(studentExperience -> mapper.map(studentExperience, Student_Experience_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Student_Experience_DTO updateStudentExperience(Student_Experience_Key studentExperienceKey, Student_Experience_DTO updatedStudentExperienceDTO) throws StudentExperienceNotFoundException {
        Optional<Student_Experience> studentExperienceOptional = studentExperienceRepo.findById(studentExperienceKey);

        if (studentExperienceOptional.isPresent()) {
            Student_Experience existingStudentExperience = studentExperienceOptional.get();
            existingStudentExperience.setNoOfYears(updatedStudentExperienceDTO.getNoOfYears());

            Student_Experience updatedStudentExperience = studentExperienceRepo.save(existingStudentExperience);
            return mapper.map(updatedStudentExperience, Student_Experience_DTO.class);
        } else {
            throw new StudentExperienceNotFoundException("Student Experience not found with ID: " + studentExperienceKey);
        }
    }

    @Override
    public boolean deleteStudentExperience(Student_Experience_Key studentExperienceKey) throws StudentExperienceNotFoundException {
        Optional<Student_Experience> studentExperienceOptional = studentExperienceRepo.findById(studentExperienceKey);

        if (studentExperienceOptional.isPresent()) {
            studentExperienceRepo.delete(studentExperienceOptional.get());
            return true;
        } else {
            throw new StudentExperienceNotFoundException("Student Experience not found with ID: " + studentExperienceKey);
        }
    }
}
