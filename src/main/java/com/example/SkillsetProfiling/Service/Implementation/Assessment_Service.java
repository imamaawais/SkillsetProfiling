package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Assessment_DTO;
import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Exception.AssessmentNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateAssessmentException;
import com.example.SkillsetProfiling.Repository.Assessment_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAssessment_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Assessment_Service implements IAssessment_Service {

    private final Assessment_Repo assessmentRepo;
    private final ModelMapper mapper;

    @Override
    public Assessment_DTO addAssessment(Assessment_DTO assessmentDto) {
        Assessment assessment = mapper.map(assessmentDto, Assessment.class);

        Assessment savedAssessment = assessmentRepo.save(assessment);
        return mapper.map(savedAssessment, Assessment_DTO.class);
    }

    @Override
    public Assessment_DTO getAssessmentById(Integer assessmentId) throws AssessmentNotFoundException {
        Optional<Assessment> assessmentOptional = assessmentRepo.findById(assessmentId);
        if (assessmentOptional.isPresent()) {
            return mapper.map(assessmentOptional.get(), Assessment_DTO.class);
        } else {
            throw new AssessmentNotFoundException("Assessment not found with ID: " + assessmentId);
        }
    }

    @Override
    @Transactional
    public List<Assessment_DTO> getAssessmentByStudentId(Integer studentId) throws AssessmentNotFoundException {
        Optional<List<Assessment>> assessmentOptional = assessmentRepo.findAllByStudentID_StudentID(studentId);
        if (assessmentOptional.isPresent()) {
            return assessmentOptional.get().stream()
                    .map(assessment -> mapper.map(assessment, Assessment_DTO.class))
                    .collect(Collectors.toList());
        } else {
            throw new AssessmentNotFoundException("Assessment not found with ID: " + studentId);
        }
    }

    @Override
    public List<Assessment_DTO> getAllAssessments() {
        List<Assessment> assessments = assessmentRepo.findAll();
        return assessments.stream()
                .map(assessment -> mapper.map(assessment, Assessment_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Assessment_DTO updateAssessment(Integer assessmentId, Assessment_DTO updatedAssessmentDTO) throws AssessmentNotFoundException {
        Optional<Assessment> assessmentOptional = assessmentRepo.findById(assessmentId);
        if (assessmentOptional.isPresent()) {
            Assessment existingAssessment = assessmentOptional.get();
            // Update fields as needed
            existingAssessment.setQuestionPaper(updatedAssessmentDTO.getQuestionPaper());
            existingAssessment.setStudentDetails(updatedAssessmentDTO.getStudentDetails());
            existingAssessment.setTimeTaken(updatedAssessmentDTO.getTimeTaken());
            existingAssessment.setTotalScore(updatedAssessmentDTO.getTotalScore());
            existingAssessment.setAssessmentTimestamp(updatedAssessmentDTO.getAssessmentTimestamp());
            existingAssessment.setIsPassed(updatedAssessmentDTO.getIsPassed());

            Assessment updatedAssessment = assessmentRepo.save(existingAssessment);
            return mapper.map(updatedAssessment, Assessment_DTO.class);
        } else {
            throw new AssessmentNotFoundException("Assessment not found with ID: " + assessmentId);
        }
    }

    @Override
    public boolean deleteAssessment(Integer assessmentId) throws AssessmentNotFoundException {
        Optional<Assessment> assessmentOptional = assessmentRepo.findById(assessmentId);
        if (assessmentOptional.isPresent()) {
            assessmentRepo.delete(assessmentOptional.get());
            return true;
        } else {
            throw new AssessmentNotFoundException("Assessment not found with ID: " + assessmentId);
        }
    }
}
