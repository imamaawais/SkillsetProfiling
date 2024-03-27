package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Bank_DTO;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionBankException;
import com.example.SkillsetProfiling.Exception.QuestionBankNotFoundException;
import com.example.SkillsetProfiling.Repository.Question_Bank_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Bank_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Question_Bank_Service implements IQuestion_Bank_Service {
    private Question_Bank_Repo questionBankRepo;
    private ModelMapper mapper;


    @Override
    public Question_Bank_DTO addQuestionBank(Question_Bank_DTO questionBankDTO) {
        Question_Bank questionBank = mapper.map(questionBankDTO, Question_Bank.class);

        // Check if a question with the same ID already exists
        if (questionBankRepo.findById(questionBank.getQuestionID()).isPresent())
            throw new DuplicateQuestionBankException("Question with the same ID already exists: " + questionBank.getQuestionID());

        // Save to the database
        Question_Bank savedQuestionBank = questionBankRepo.save(questionBank);
        Question_Bank_DTO savedQuestionBankDTO = mapper.map(savedQuestionBank, Question_Bank_DTO.class);
        return savedQuestionBankDTO;
    }

    @Override
    public Question_Bank_DTO getQuestionBankByQuestionID(Integer questionID) {
        Optional<Question_Bank> questionBankOptional = questionBankRepo.findById(questionID);

        if (questionBankOptional.isPresent()) {
            return mapper.map(questionBankOptional.get(), Question_Bank_DTO.class);
        } else {
            throw new QuestionBankNotFoundException("Question bank not found with ID: " + questionID);
        }
    }

    @Override
    public List<Question_Bank_DTO> getAllQuestionBanks() {
        List<Question_Bank> allQuestionBanks = questionBankRepo.findAll();
        return allQuestionBanks.stream()
                .map(questionBank -> mapper.map(questionBank, Question_Bank_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Question_Bank_DTO updateQuestionBank(Integer questionID, Question_Bank_DTO updatedQuestionBankDTO) {
        Optional<Question_Bank> existingQuestionBankOptional = questionBankRepo.findById(questionID);

        if (existingQuestionBankOptional.isPresent()) {
            Question_Bank existingQuestionBank = existingQuestionBankOptional.get();
            // Update question bank based on updatedQuestionBankDTO
            // Update other fields as needed

            existingQuestionBank.setQuestionText(updatedQuestionBankDTO.getQuestionText());
            existingQuestionBank.setAnswer(updatedQuestionBankDTO.getAnswer());
            existingQuestionBank.setQuestionDifficulty(updatedQuestionBankDTO.getQuestionDifficulty());
            existingQuestionBank.setMCQ(updatedQuestionBankDTO.isMCQ());
            existingQuestionBank.setOptionA(updatedQuestionBankDTO.getOptionA());
            existingQuestionBank.setOptionB(updatedQuestionBankDTO.getOptionB());
            existingQuestionBank.setOptionC(updatedQuestionBankDTO.getOptionC());
            existingQuestionBank.setOptionD(updatedQuestionBankDTO.getOptionD());

            Question_Bank updatedQuestionBank = questionBankRepo.save(existingQuestionBank);
            return mapper.map(updatedQuestionBank, Question_Bank_DTO.class);
        } else {
            throw new QuestionBankNotFoundException("Question bank not found with ID: " + questionID);
        }
    }

    @Override
    @Transactional
    public boolean deleteQuestionBank(Integer questionID) {
        Optional<Question_Bank> questionBankOptional = questionBankRepo.findById(questionID);

        if (questionBankOptional.isPresent()) {
            questionBankRepo.deleteById(questionID);
            return true; // Deletion successful
        } else {
            throw new QuestionBankNotFoundException("Question bank not found with ID: " + questionID);
        }
    }



}
