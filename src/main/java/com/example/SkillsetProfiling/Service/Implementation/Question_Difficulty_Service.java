package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Difficulty_DTO;
import com.example.SkillsetProfiling.Entity.Question_Difficulty;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionDifficultyException;
import com.example.SkillsetProfiling.Exception.QuestionDifficultyNotFoundException;
import com.example.SkillsetProfiling.Repository.Question_Difficulty_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Difficulty_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Question_Difficulty_Service implements IQuestion_Difficulty_Service {
    private Question_Difficulty_Repo questionDifficultyRepo;
    private ModelMapper mapper;


    @Override
    public Question_Difficulty_DTO addQuestionDifficulty(Question_Difficulty_DTO questionDifficultyDTO) {
        Question_Difficulty questionDifficulty = mapper.map(questionDifficultyDTO, Question_Difficulty.class);


        // Save to the database
        Question_Difficulty savedQuestionDifficulty = questionDifficultyRepo.save(questionDifficulty);
        Question_Difficulty_DTO savedQuestionDifficultyDTO = mapper.map(savedQuestionDifficulty, Question_Difficulty_DTO.class);
        return savedQuestionDifficultyDTO;
    }

    @Override
    public Question_Difficulty_DTO getQuestionDifficultyByDifficultyID(Integer difficultyID) {
        Optional<Question_Difficulty> questionDifficultyOptional = questionDifficultyRepo.findById(difficultyID);

        if (questionDifficultyOptional.isPresent()) {
            return mapper.map(questionDifficultyOptional.get(), Question_Difficulty_DTO.class);
        } else {
            throw new QuestionDifficultyNotFoundException("Question difficulty not found with ID: " + difficultyID);
        }
    }

    @Override
    public List<Question_Difficulty_DTO> getAllQuestionDifficulties() {
        List<Question_Difficulty> allQuestionDifficulties = questionDifficultyRepo.findAll();
        return allQuestionDifficulties.stream()
                .map(questionDifficulty -> mapper.map(questionDifficulty, Question_Difficulty_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Difficulty_DTO updateQuestionDifficulty(Integer difficultyID, Question_Difficulty_DTO updatedQuestionDifficultyDTO) {
        Optional<Question_Difficulty> existingQuestionDifficultyOptional = questionDifficultyRepo.findById(difficultyID);

        if (existingQuestionDifficultyOptional.isPresent()) {
            Question_Difficulty existingQuestionDifficulty = existingQuestionDifficultyOptional.get();
            // Update question difficulty based on updatedQuestionDifficultyDTO
            // Update other fields as needed

            existingQuestionDifficulty.setMarks(updatedQuestionDifficultyDTO.getMarks());
            existingQuestionDifficulty.setTimeAllotted(updatedQuestionDifficultyDTO.getTimeAllotted());

            Question_Difficulty updatedQuestionDifficulty = questionDifficultyRepo.save(existingQuestionDifficulty);
            return mapper.map(updatedQuestionDifficulty, Question_Difficulty_DTO.class);
        } else {
            throw new QuestionDifficultyNotFoundException("Question difficulty not found with ID: " + difficultyID);
        }
    }

    @Override
    @Transactional
    public boolean deleteQuestionDifficulty(Integer difficultyID) {
        Optional<Question_Difficulty> questionDifficultyOptional = questionDifficultyRepo.findById(difficultyID);

        if (questionDifficultyOptional.isPresent()) {
            questionDifficultyRepo.deleteById(difficultyID);
            return true; // Deletion successful
        } else {
            throw new QuestionDifficultyNotFoundException("Question difficulty not found with ID: " + difficultyID);
        }
    }



}
