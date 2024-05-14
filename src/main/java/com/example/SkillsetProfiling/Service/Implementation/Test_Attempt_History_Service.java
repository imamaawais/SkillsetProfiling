package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Test_Attempt_History_DTO;
import com.example.SkillsetProfiling.Dto.Test_DTO;
import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Entity.Test;
import com.example.SkillsetProfiling.Entity.Test_Attempt_History;
import com.example.SkillsetProfiling.Exception.DuplicateTestAttemptHistoryException;
import com.example.SkillsetProfiling.Exception.TestAttemptHistoryNotFoundException;
import com.example.SkillsetProfiling.Key.Student_Skills_Key;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
import com.example.SkillsetProfiling.Repository.*;
import com.example.SkillsetProfiling.Service.Interface.ITest_Attempt_History_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Test_Attempt_History_Service implements ITest_Attempt_History_Service {

    private final Test_Attempt_History_Repo testAttemptHistoryRepo;
    private final Test_Repo testRepo;
    private final Student_Skills_Repo studentSkillsRepo;
    private final Skill_Level_Repo skillLevelRepo;
    private final Assessment_Repo assessmentRepo;
    private final ModelMapper modelMapper;

    @Override
    public Test_Attempt_History_DTO addTestAttemptHistory(Test_Attempt_History_DTO testAttemptHistoryDTO) {
        Test_Attempt_History testAttemptHistory = modelMapper.map(testAttemptHistoryDTO, Test_Attempt_History.class);
        Optional<Test_Attempt_History> existingTest = testAttemptHistoryRepo.findById(new Test_Attempt_History_Key(testAttemptHistory.getTestID().getTestID(), testAttemptHistory.getAssessmentID().getAssessmentID()));

        if (existingTest.isPresent()) {
            Test_Attempt_History updatedTest = existingTest.get();
            testAttemptHistory.setAttemptNumber(updatedTest.getAttemptNumber() + 1);
        }

        Test attemptedTest = testRepo.findById(testAttemptHistoryDTO.getTest().getTestID()).get();
        Assessment attemptedAssessment = assessmentRepo.findById(testAttemptHistoryDTO.getAssessment().getAssessmentID()).get();

        attemptedTest.setIsPassed(attemptedAssessment.getIsPassed());

        if(attemptedTest.getIsPassed()) {
            List<Test> allTests = testRepo.findTestByStudentSkillLevel(attemptedTest.getStudentSkillLevel().getStudentDetails().getStudentID(), attemptedTest.getStudentSkillLevel().getSkills().getSkillID(), attemptedTest.getStudentSkillLevel().getSkillLevel().getLevelID());

            boolean allTestsPassed = true;
            for (Test test : allTests) {
                if(!Objects.equals(test.getTestID(), attemptedTest.getTestID())){
                    if (test.getIsPassed() == null) {
                        allTestsPassed = false;
                        break;
                    }
                    if (!test.getIsPassed()) {
                        allTestsPassed = false;
                        break;
                    }
                }
            }

            if (allTestsPassed) {
                // All tests are passed
                Optional<Student_Skills> studentSkills = studentSkillsRepo.findById(new Student_Skills_Key(attemptedTest.getStudentSkillLevel().getStudentDetails().getStudentID(), attemptedTest.getStudentSkillLevel().getSkills().getSkillID()));
                if(studentSkills.get().getLevelID() == null){
                    studentSkills.get().setLevelID(skillLevelRepo.findById(1).get());
                }
                else{
                    studentSkills.get().setLevelID(skillLevelRepo.findById(studentSkills.get().getLevelID().getLevelID() + 1).get());
                }
            }
        }
        if (attemptedTest.getNoOfAttempts() == null) {
            attemptedTest.setNoOfAttempts(1);
        }
        else{
            attemptedTest.setNoOfAttempts(attemptedTest.getNoOfAttempts() + 1);
        }
        testRepo.save(attemptedTest);
        System.out.println("Test ID: " + attemptedTest.getTestID());

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
