package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Question_Bank_DTO;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Exception.DuplicateQuestionBankException;
import com.example.SkillsetProfiling.Exception.QuestionBankNotFoundException;
import com.example.SkillsetProfiling.Repository.Question_Bank_Repo;
import com.example.SkillsetProfiling.Repository.Question_Sub_Skill_Repo;
import com.example.SkillsetProfiling.Service.Interface.IQuestion_Bank_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Question_Bank_Service implements IQuestion_Bank_Service {
    private Question_Bank_Repo questionBankRepo;
    private Question_Sub_Skill_Repo questionSubSkillRepo;
    private ModelMapper mapper;


    @Override
    public Question_Bank_DTO addQuestionBank(Question_Bank_DTO questionBankDTO) {
        Question_Bank questionBank = mapper.map(questionBankDTO, Question_Bank.class);


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
    public List<Question_Bank_DTO> getQuestionsBySkillAndLevel(Integer skillID, Integer skillLevelID) {
        // Retrieve questions for different difficulty levels
        List<Question_Bank> questionsLevel1 = questionSubSkillRepo.findQuestionBySkillAndDifficulty(skillID, skillLevelID);
        List<Question_Bank> questionsLevel2 = questionSubSkillRepo.findQuestionBySkillAndDifficulty(skillID, skillLevelID + 1);
        List<Question_Bank> questionsLevel3 = questionSubSkillRepo.findQuestionBySkillAndDifficulty(skillID, skillLevelID + 2);

        // Shuffle each list of questions
        Collections.shuffle(questionsLevel1);
        Collections.shuffle(questionsLevel2);
        Collections.shuffle(questionsLevel3);

        // Select the required number of questions from each list
        List<Question_Bank> selectedQuestions = new ArrayList<>();
        selectedQuestions.addAll(selectRandomQuestions(questionsLevel1, 6));
        selectedQuestions.addAll(selectRandomQuestions(questionsLevel2, 8));
        selectedQuestions.addAll(selectRandomQuestions(questionsLevel3, 6));

        return selectedQuestions.stream()
                .map(questionBank -> mapper.map(questionBank, Question_Bank_DTO.class))
                .collect(Collectors.toList());
    }

    // Method to select random questions from a list without repetition
    private List<Question_Bank> selectRandomQuestions(List<Question_Bank> questions, int count) {
        Set<Question_Bank> selected = new HashSet<>();
        while (selected.size() < count && !questions.isEmpty()) {
            int randomIndex = (int) (Math.random() * questions.size());
            selected.add(questions.remove(randomIndex));
        }
        return new ArrayList<>(selected);
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
