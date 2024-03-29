package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Assessment_Scores_DTO;
import com.example.SkillsetProfiling.Entity.Assessment_Scores;
import com.example.SkillsetProfiling.Exception.AssessmentScoreNotFoundException;
import com.example.SkillsetProfiling.Exception.DuplicateAssessmentScoreException;
import com.example.SkillsetProfiling.Key.Assessment_Scores_Key;
import com.example.SkillsetProfiling.Repository.Assessment_Scores_Repo;
import com.example.SkillsetProfiling.Service.Interface.IAssessment_Scores_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Assessment_Scores_Service implements IAssessment_Scores_Service {

    private final Assessment_Scores_Repo assessmentScoresRepo;
    private final ModelMapper mapper;

    @Override
    public Assessment_Scores_DTO addAssessmentScore(Assessment_Scores_DTO assessmentScoresDTO) {
        Assessment_Scores assessmentScores = mapper.map(assessmentScoresDTO, Assessment_Scores.class);

        if (assessmentScoresRepo.existsById(new Assessment_Scores_Key(assessmentScores.getAssessmentID().getAssessmentID(), assessmentScores.getQuestionID().getQuestionID()))) {
            throw new DuplicateAssessmentScoreException("Assessment score already exists with IDs: " + assessmentScores.getAssessmentID().getAssessmentID() + ", " + assessmentScores.getQuestionID().getQuestionID());
        }

        Assessment_Scores savedAssessmentScores = assessmentScoresRepo.save(assessmentScores);
        return mapper.map(savedAssessmentScores, Assessment_Scores_DTO.class);
    }

    @Override
    public Assessment_Scores_DTO getAssessmentScoreByIds(Integer assessmentId, Integer questionId) throws AssessmentScoreNotFoundException {
        Optional<Assessment_Scores> assessmentScoresOptional = assessmentScoresRepo.findById(new Assessment_Scores_Key(assessmentId, questionId));
        if (assessmentScoresOptional.isPresent()) {
            return mapper.map(assessmentScoresOptional.get(), Assessment_Scores_DTO.class);
        } else {
            throw new AssessmentScoreNotFoundException("Assessment score not found with IDs: " + assessmentId + ", " + questionId);
        }
    }

    @Override
    public List<Assessment_Scores_DTO> getAllAssessmentScores() {
        List<Assessment_Scores> assessmentScores = assessmentScoresRepo.findAll();
        return assessmentScores.stream()
                .map(score -> mapper.map(score, Assessment_Scores_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Assessment_Scores_DTO updateAssessmentScore(Integer assessmentId, Integer questionId, Assessment_Scores_DTO updatedAssessmentScoresDTO) throws AssessmentScoreNotFoundException {
        Optional<Assessment_Scores> assessmentScoresOptional = assessmentScoresRepo.findById(new Assessment_Scores_Key(assessmentId, questionId));
        if (assessmentScoresOptional.isPresent()) {
            Assessment_Scores existingAssessmentScores = assessmentScoresOptional.get();
            existingAssessmentScores.setIsAttempted(updatedAssessmentScoresDTO.getIsAttempted());
            existingAssessmentScores.setUserAnswer(updatedAssessmentScoresDTO.getUserAnswer());

            Assessment_Scores updatedAssessmentScores = assessmentScoresRepo.save(existingAssessmentScores);
            return mapper.map(updatedAssessmentScores, Assessment_Scores_DTO.class);
        } else {
            throw new AssessmentScoreNotFoundException("Assessment score not found with IDs: " + assessmentId + ", " + questionId);
        }
    }

    @Override
    public boolean deleteAssessmentScore(Integer assessmentId, Integer questionId) throws AssessmentScoreNotFoundException {
        Optional<Assessment_Scores> assessmentScoresOptional = assessmentScoresRepo.findById(new Assessment_Scores_Key(assessmentId, questionId));
        if (assessmentScoresOptional.isPresent()) {
            assessmentScoresRepo.delete(assessmentScoresOptional.get());
            return true;
        } else {
            throw new AssessmentScoreNotFoundException("Assessment score not found with IDs: " + assessmentId + ", " + questionId);
        }
    }
}
