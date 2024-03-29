package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Test_Attempt_History_DTO;
import com.example.SkillsetProfiling.Entity.Test_Attempt_History;
import com.example.SkillsetProfiling.Exception.DuplicateTestAttemptHistoryException;
import com.example.SkillsetProfiling.Exception.TestAttemptHistoryNotFoundException;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
import com.example.SkillsetProfiling.Repository.Test_Attempt_History_Repo;
import com.example.SkillsetProfiling.Service.Interface.ITest_Attempt_History_Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Test_Attempt_History_Service implements ITest_Attempt_History_Service {

    private final Test_Attempt_History_Repo testAttemptHistoryRepo;
    private final ModelMapper modelMapper;

    public Test_Attempt_History_Service(Test_Attempt_History_Repo testAttemptHistoryRepo, ModelMapper modelMapper) {
        this.testAttemptHistoryRepo = testAttemptHistoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Test_Attempt_History_DTO addTestAttemptHistory(Test_Attempt_History_DTO testAttemptHistoryDTO) {
        Test_Attempt_History testAttemptHistory = modelMapper.map(testAttemptHistoryDTO, Test_Attempt_History.class);
        if (testAttemptHistoryRepo.findById(new Test_Attempt_History_Key(testAttemptHistory.getTestID().getTestID(), testAttemptHistory.getAssessmentID().getAssessmentID())).isPresent()) {
            throw new DuplicateTestAttemptHistoryException("Test attempt history already exists with ID: " + testAttemptHistory.getTestID().getTestID());
        }
        Test_Attempt_History savedTestAttemptHistory = testAttemptHistoryRepo.save(testAttemptHistory);
        return modelMapper.map(savedTestAttemptHistory, Test_Attempt_History_DTO.class);
    }

    @Override
    public Test_Attempt_History_DTO getTestAttemptHistoryByIds(Integer testId, Integer assessmentId) throws TestAttemptHistoryNotFoundException {
        Optional<Test_Attempt_History> testAttemptHistoryOptional = testAttemptHistoryRepo.findById(new Test_Attempt_History_Key(testId, assessmentId));
        if (testAttemptHistoryOptional.isPresent()) {
            return modelMapper.map(testAttemptHistoryOptional.get(), Test_Attempt_History_DTO.class);
        } else {
            throw new TestAttemptHistoryNotFoundException("Test attempt history not found with IDs: " + testId + ", " + assessmentId);
        }
    }

    @Override
    public List<Test_Attempt_History_DTO> getAllTestAttemptHistory() {
        List<Test_Attempt_History> testAttemptHistoryList = testAttemptHistoryRepo.findAll();
        return testAttemptHistoryList.stream()
                .map(history -> modelMapper.map(history, Test_Attempt_History_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Test_Attempt_History_DTO updateTestAttemptHistory(Integer testId, Integer assessmentId, Test_Attempt_History_DTO updatedTestAttemptHistoryDTO) throws TestAttemptHistoryNotFoundException {
        Optional<Test_Attempt_History> testAttemptHistoryOptional = testAttemptHistoryRepo.findById(new Test_Attempt_History_Key(testId, assessmentId));
        if (testAttemptHistoryOptional.isPresent()) {
            Test_Attempt_History existingTestAttemptHistory = testAttemptHistoryOptional.get();
            existingTestAttemptHistory.setAttemptNumber(updatedTestAttemptHistoryDTO.getAttemptNumber());

            Test_Attempt_History updatedTestAttemptHistory = testAttemptHistoryRepo.save(existingTestAttemptHistory);
            return modelMapper.map(updatedTestAttemptHistory, Test_Attempt_History_DTO.class);
        } else {
            throw new TestAttemptHistoryNotFoundException("Test attempt history not found with IDs: " + testId + ", " + assessmentId);
        }
    }

    @Override
    public boolean deleteTestAttemptHistory(Integer testId, Integer assessmentId) throws TestAttemptHistoryNotFoundException {
        Optional<Test_Attempt_History> testAttemptHistoryOptional = testAttemptHistoryRepo.findById(new Test_Attempt_History_Key(testId, assessmentId));
        if (testAttemptHistoryOptional.isPresent()) {
            testAttemptHistoryRepo.delete(testAttemptHistoryOptional.get());
            return true;
        } else {
            throw new TestAttemptHistoryNotFoundException("Test attempt history not found with IDs: " + testId + ", " + assessmentId);
        }
    }
}
