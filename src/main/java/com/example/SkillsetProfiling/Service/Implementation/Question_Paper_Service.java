package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Paper_DTO;
import com.example.SkillsetProfiling.Entity.Question_Paper;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionPaperException;
import com.example.SkillsetProfiling.Exception.QuestionPaperNotFoundException;
import com.example.SkillsetProfiling.Repository.Question_Paper_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Paper_Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class Question_Paper_Service implements IQuestion_Paper_Service {

    private final Question_Paper_Repo questionPaperRepo;
    private final ModelMapper mapper;

    public Question_Paper_Service(Question_Paper_Repo questionPaperRepo, ModelMapper mapper) {
        this.questionPaperRepo = questionPaperRepo;
        this.mapper = mapper;
    }

    @Override
    public Question_Paper_DTO addQuestionPaper(Question_Paper_DTO questionPaperDTO) {
        // Map the Question_Paper_DTO to the Question_Paper entity
        Question_Paper questionPaper = mapper.map(questionPaperDTO, Question_Paper.class);



        // No question paper with the same ID, proceed to save the new question paper
        Question_Paper savedQuestionPaper = questionPaperRepo.save(questionPaper);
        Question_Paper_DTO savedQuestionPaperDTO = mapper.map(savedQuestionPaper, Question_Paper_DTO.class);
        // Map the saved Question_Paper entity back to Question_Paper_DTO
        return savedQuestionPaperDTO;
    }


    @Override
    public Question_Paper_DTO getQuestionPaperById(Integer questionPaperId) throws QuestionPaperNotFoundException {
        Optional<Question_Paper> questionPaperOptional = questionPaperRepo.findById(questionPaperId);
        if (questionPaperOptional.isPresent()) {
            return mapper.map(questionPaperOptional.get(), Question_Paper_DTO.class);
        } else {
            throw new QuestionPaperNotFoundException("Question paper not found with ID: " + questionPaperId);
        }
    }

    @Override
    public Question_Paper_DTO getRandomQuestionPaper(Integer skillID, Integer skillLevelID) throws QuestionPaperNotFoundException {
        List<Question_Paper> questionPapers = questionPaperRepo.findAllBySkillIDAndSkillLevel(skillID, skillLevelID);

        if (questionPapers.isEmpty()) {
            throw new QuestionPaperNotFoundException("No question papers found for the provided skill ID and skill level ID");
        }

        // Generate a random index within the size of the questionPapers list
        Random random = new Random();
        int randomIndex = random.nextInt(questionPapers.size());

        // Get the randomly selected question paper
        Question_Paper randomQuestionPaper = questionPapers.get(randomIndex);

        return mapper.map(randomQuestionPaper, Question_Paper_DTO.class);
    }

    @Override
    public List<Question_Paper_DTO> getAllQuestionPapers() {
        List<Question_Paper> questionPapers = questionPaperRepo.findAll();
        return questionPapers.stream()
                .map(questionPaper -> mapper.map(questionPaper, Question_Paper_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Paper_DTO updateQuestionPaper(Integer questionPaperId, Question_Paper_DTO updatedQuestionPaperDTO) throws QuestionPaperNotFoundException {
        Optional<Question_Paper> questionPaperOptional = questionPaperRepo.findById(questionPaperId);
        if (questionPaperOptional.isPresent()) {
            Question_Paper existingQuestionPaper = questionPaperOptional.get();
            existingQuestionPaper.setSkillLevel(updatedQuestionPaperDTO.getSkillLevel());
            existingQuestionPaper.setSkillID(updatedQuestionPaperDTO.getSkillID());
            existingQuestionPaper.setTimestampUpdated(updatedQuestionPaperDTO.getTimestampUpdated());
            existingQuestionPaper.setTimestampCreated(updatedQuestionPaperDTO.getTimestampCreated());

            Question_Paper updatedQuestionPaper = questionPaperRepo.save(existingQuestionPaper);
            return mapper.map(updatedQuestionPaper, Question_Paper_DTO.class);
        } else {
            throw new QuestionPaperNotFoundException("Question paper not found with ID: " + questionPaperId);
        }
    }

    @Override
    public boolean deleteQuestionPaper(Integer questionPaperId) throws QuestionPaperNotFoundException {
        Optional<Question_Paper> questionPaperOptional = questionPaperRepo.findById(questionPaperId);
        if (questionPaperOptional.isPresent()) {
            questionPaperRepo.delete(questionPaperOptional.get());
            return true;
        } else {
            throw new QuestionPaperNotFoundException("Question paper not found with ID: " + questionPaperId);
        }
    }
}
