package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Student_Details_DTO;
import com.example.SkillsetProfiling.Entity.Student_Details;
import com.example.SkillsetProfiling.Exception.DuplicateStudentDetailsException;
import com.example.SkillsetProfiling.Exception.StudentDetailsNotFoundException;
import com.example.SkillsetProfiling.Repository.Student_Details_Repo;
import com.example.SkillsetProfiling.Service.Interface.IStudent_Details_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Student_Details_Service implements IStudent_Details_Service {


    private Student_Details_Repo studentDetailsRepo;
    private ModelMapper mapper;

    @Override
    public Student_Details_DTO addStudentDetails(Student_Details_DTO studentDetailsDTO) {
        Student_Details studentDetails = mapper.map(studentDetailsDTO, Student_Details.class);


        // Save to the database
        Student_Details savedStudentDetails = studentDetailsRepo.save(studentDetails);
        Student_Details_DTO savedStudentDetailsDTO = mapper.map(savedStudentDetails, Student_Details_DTO.class);
        return savedStudentDetailsDTO;
    }

    @Override
    public Student_Details_DTO getStudentDetailsByStudentID(Integer studentID) {
        Optional<Student_Details> studentDetailsOptional = studentDetailsRepo.findById(studentID);

        if (studentDetailsOptional.isPresent()) {
            return mapper.map(studentDetailsOptional.get(), Student_Details_DTO.class);
        } else {
            throw new StudentDetailsNotFoundException("Student details not found with ID: " + studentID);
        }
    }

    @Override
    public Integer getStudentIDByEmail(String email) {
        Optional<Integer> studentDetailsOptional = studentDetailsRepo.findByUserDetails_Auth_Email(email);

        if (studentDetailsOptional.isPresent()) {
            return studentDetailsOptional.get();
        } else {
            throw new StudentDetailsNotFoundException("Student details not found with email: " + email);
        }
    }

    @Override
    public List<Student_Details_DTO> getAllStudentDetails() {
        List<Student_Details> allStudentDetails = studentDetailsRepo.findAll();
        return allStudentDetails.stream()
                .map(studentDetails -> mapper.map(studentDetails, Student_Details_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Student_Details_DTO updateStudentDetails(Integer studentID, Student_Details_DTO updatedStudentDetailsDTO) {
        Optional<Student_Details> existingStudentDetailsOptional = studentDetailsRepo.findById(studentID);

        if (existingStudentDetailsOptional.isPresent()) {
            Student_Details existingStudentDetails = existingStudentDetailsOptional.get();
            // Update student details based on updatedStudentDetailsDTO
            existingStudentDetails.setEnrollmentYear(updatedStudentDetailsDTO.getEnrollmentYear());
            existingStudentDetails.setExpectedGraduation(updatedStudentDetailsDTO.getExpectedGraduation());
            existingStudentDetails.setUserDetails(updatedStudentDetailsDTO.getUserDetails());
            existingStudentDetails.setDomain(updatedStudentDetailsDTO.getDomain());
            existingStudentDetails.setResume(updatedStudentDetailsDTO.getResume());

            Student_Details updatedStudentDetails = studentDetailsRepo.save(existingStudentDetails);
            return mapper.map(updatedStudentDetails, Student_Details_DTO.class);
        } else {
            throw new StudentDetailsNotFoundException("Student details not found with ID: " + studentID);
        }
    }

    @Override
    @Transactional
    public boolean deleteStudentDetails(Integer studentID) {
        Optional<Student_Details> studentDetailsOptional = studentDetailsRepo.findById(studentID);

        if (studentDetailsOptional.isPresent()) {
            studentDetailsRepo.deleteById(studentID);
            return true; // Deletion successful
        } else {
            throw new StudentDetailsNotFoundException("Student details not found with ID: " + studentID);
        }
    }
}
