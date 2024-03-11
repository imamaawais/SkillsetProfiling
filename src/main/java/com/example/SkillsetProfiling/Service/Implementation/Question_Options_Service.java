package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Options_DTO;
import com.example.SkillsetProfiling.Dto.Question_Options_DTO;
import com.example.SkillsetProfiling.Dto.Question_Options_DTO;
import com.example.SkillsetProfiling.Entity.Question_Options;
import com.example.SkillsetProfiling.Entity.Question_Options;
import com.example.SkillsetProfiling.Entity.Question_Options;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionOptionsException;
import com.example.SkillsetProfiling.Exception.QuestionOptionsNotFoundException;
import com.example.SkillsetProfiling.Repository.Question_Options_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Options_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Question_Options_Service implements IQuestion_Options_Service {
    private Question_Options_Repo questionOptionsRepo;
    private ModelMapper mapper;
    
    @Override
    public Question_Options_DTO addQuestionOption(Question_Options_DTO questionOptionsDTO) {
        Question_Options questionOptions = mapper.map(questionOptionsDTO, Question_Options.class);

        // Check if a question with the same ID already exists
        if (questionOptionsRepo.findById(questionOptions.getQuestionID().getQuestionID()).isPresent())
            throw new DuplicateQuestionOptionsException("Question with the same ID already exists: " + questionOptions.getQuestionID());

        // Save to the database
        Question_Options savedQuestionOptions = questionOptionsRepo.save(questionOptions);
        Question_Options_DTO savedQuestionOptionsDTO = mapper.map(savedQuestionOptions, Question_Options_DTO.class);
        return savedQuestionOptionsDTO;
    }

    @Override
    public Question_Options_DTO getQuestionOptionByQuestionID(Integer questionID) {
        Optional<Question_Options> questionOptionsOptional = questionOptionsRepo.findById(questionID);

        if (questionOptionsOptional.isPresent()) {
            return mapper.map(questionOptionsOptional.get(), Question_Options_DTO.class);
        } else {
            throw new QuestionOptionsNotFoundException("Question not found with ID: " + questionID);
        }
    }

    @Override
    public List<Question_Options_DTO> getAllQuestionOptions() {
        List<Question_Options> allQuestionOptions = questionOptionsRepo.findAll();
        return allQuestionOptions.stream()
                .map(questionOptions -> mapper.map(questionOptions, Question_Options_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Options_DTO updateQuestionOption(Integer questionID, Question_Options_DTO updatedQuestionOptionsDTO) {
        Optional<Question_Options> existingQuestionOptionsOptional = questionOptionsRepo.findById(questionID);

        if (existingQuestionOptionsOptional.isPresent()) {
            Question_Options existingQuestionOptions = existingQuestionOptionsOptional.get();
            // Update question bank based on updatedQuestionOptionsDTO
            // Update other fields as needed

            existingQuestionOptions.setOptionA(updatedQuestionOptionsDTO.getOptionA());
            existingQuestionOptions.setOptionB(updatedQuestionOptionsDTO.getOptionB());
            existingQuestionOptions.setOptionC(updatedQuestionOptionsDTO.getOptionC());
            existingQuestionOptions.setOptionD(updatedQuestionOptionsDTO.getOptionD());

            Question_Options updatedQuestionOptions = questionOptionsRepo.save(existingQuestionOptions);
            return mapper.map(updatedQuestionOptions, Question_Options_DTO.class);
        } else {
            throw new QuestionOptionsNotFoundException("Question not found with ID: " + questionID);
        }
    }

    @Override
    public boolean deleteQuestionOption(Integer questionID) {
        Optional<Question_Options> questionOptionsOptional = questionOptionsRepo.findById(questionID);

        if (questionOptionsOptional.isPresent()) {
            questionOptionsRepo.deleteById(questionID);
            return true; // Deletion successful
        } else {
            throw new QuestionOptionsNotFoundException("Question not found with ID: " + questionID);
        }
    }
}
