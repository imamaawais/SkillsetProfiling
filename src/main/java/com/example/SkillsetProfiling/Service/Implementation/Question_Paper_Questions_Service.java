package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Paper_Questions_DTO;
import com.example.SkillsetProfiling.Entity.Question_Paper_Questions;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionPaperQuesitonException;
import com.example.SkillsetProfiling.Exception.QuestionPaperQuestionsNotFoundException;
import com.example.SkillsetProfiling.Key.Question_Paper_Questions_Key;
import com.example.SkillsetProfiling.Repository.Question_Paper_Questions_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Paper_Questions_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Question_Paper_Questions_Service implements IQuestion_Paper_Questions_Service {

    private final Question_Paper_Questions_Repo questionPaperQuestionsRepo;
    private final ModelMapper mapper;

    @Override
    public Question_Paper_Questions_DTO addQuestionPaperQuestions(Question_Paper_Questions_DTO questionPaperQuestionsDTO) {
        Question_Paper_Questions questionPaperQuestions = mapper.map(questionPaperQuestionsDTO, Question_Paper_Questions.class);

        if (questionPaperQuestionsRepo.findById(new Question_Paper_Questions_Key(questionPaperQuestions.getQuestionPaperID().getQuestionPaperID(), questionPaperQuestions.getQuestionID().getQuestionID())).isPresent())
            throw new DuplicateQuestionPaperQuesitonException("Question paper question already exists with IDs: " + questionPaperQuestions.getQuestionPaperID().getQuestionPaperID() + ", " + questionPaperQuestions.getQuestionID().getQuestionID());

        Question_Paper_Questions savedQuestionPaperQuestions = questionPaperQuestionsRepo.save(questionPaperQuestions);
        return mapper.map(savedQuestionPaperQuestions, Question_Paper_Questions_DTO.class);
    }

    @Override
    public Question_Paper_Questions_DTO getQuestionPaperQuestionsByIds(Integer questionPaperId, Integer questionId) throws QuestionPaperQuestionsNotFoundException {
        Optional<Question_Paper_Questions> questionPaperQuestionsOptional = questionPaperQuestionsRepo.findById(new Question_Paper_Questions_Key(questionPaperId, questionId));
        if (questionPaperQuestionsOptional.isPresent()) {
            return mapper.map(questionPaperQuestionsOptional.get(), Question_Paper_Questions_DTO.class);
        } else {
            throw new QuestionPaperQuestionsNotFoundException("Question paper question not found with IDs: " + questionPaperId + ", " + questionId);
        }
    }

    @Override
    public List<Question_Paper_Questions_DTO> getAllQuestionPaperQuestions() {
        List<Question_Paper_Questions> questionPaperQuestions = questionPaperQuestionsRepo.findAll();
        return questionPaperQuestions.stream()
                .map(questionPaperQuestion -> mapper.map(questionPaperQuestion, Question_Paper_Questions_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Paper_Questions_DTO updateQuestionPaperQuestions(Integer questionPaperId, Integer questionId, Question_Paper_Questions_DTO updatedQuestionPaperQuestionsDTO) throws QuestionPaperQuestionsNotFoundException {
        Optional<Question_Paper_Questions> questionPaperQuestionsOptional = questionPaperQuestionsRepo.findById(new Question_Paper_Questions_Key(questionPaperId, questionId));
        if (questionPaperQuestionsOptional.isPresent()) {
            Question_Paper_Questions existingQuestionPaperQuestions = questionPaperQuestionsOptional.get();
            existingQuestionPaperQuestions.setQuestionPaperID(updatedQuestionPaperQuestionsDTO.getQuestionPaper());
            existingQuestionPaperQuestions.setQuestionID(updatedQuestionPaperQuestionsDTO.getQuestionBank());
            Question_Paper_Questions updatedQuestionPaperQuestions = questionPaperQuestionsRepo.save(existingQuestionPaperQuestions);
            return mapper.map(updatedQuestionPaperQuestions, Question_Paper_Questions_DTO.class);
        } else {
            throw new QuestionPaperQuestionsNotFoundException("Question paper question not found with IDs: " + questionPaperId + ", " + questionId);
        }
    }

    @Override
    public boolean deleteQuestionPaperQuestions(Integer questionPaperId, Integer questionId) throws QuestionPaperQuestionsNotFoundException {
        Optional<Question_Paper_Questions> questionPaperQuestionsOptional = questionPaperQuestionsRepo.findById(new Question_Paper_Questions_Key(questionPaperId, questionId));
        if (questionPaperQuestionsOptional.isPresent()) {
            questionPaperQuestionsRepo.delete(questionPaperQuestionsOptional.get());
            return true;
        } else {
            throw new QuestionPaperQuestionsNotFoundException("Question paper question not found with IDs: " + questionPaperId + ", " + questionId);
        }
    }
}
