package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Test_DTO;
import com.example.SkillsetProfiling.Entity.Student_Skill_Level;
import com.example.SkillsetProfiling.Entity.Test;
import com.example.SkillsetProfiling.Exception.DuplicateTestException;
import com.example.SkillsetProfiling.Exception.TestNotFoundException;
import com.example.SkillsetProfiling.Repository.Student_Skill_Level_Repo;
import com.example.SkillsetProfiling.Repository.Test_Repo;
import com.example.SkillsetProfiling.Service.Interface.ITest_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Test_Service implements ITest_Service {

    private final Test_Repo testRepository;
    private final Student_Skill_Level_Repo studentSkillLevelRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Test_DTO addTest(Test_DTO testDTO) {
        Test test = modelMapper.map(testDTO, Test.class);

        if (testDTO.getStudentSkillLevel().getStudentSkillLevelID() == null) {
            Optional<Student_Skill_Level> studentSkillLevel = studentSkillLevelRepo.findByStudentSkillLevel(test.getStudentSkillLevel().getStudentDetails().getStudentID(), test.getStudentSkillLevel().getSkills().getSkillID(), test.getStudentSkillLevel().getSkillLevel().getLevelID());
            if (studentSkillLevel.isPresent()) {
                test.setStudentSkillLevel(studentSkillLevel.get());
            }
        }

        Test savedTest = testRepository.save(test);
        return modelMapper.map(savedTest, Test_DTO.class);
    }

    @Override
    public Test_DTO getTestById(Integer testId) throws TestNotFoundException {
        Optional<Test> testOptional = testRepository.findById(testId);
        if (testOptional.isPresent()) {
            return modelMapper.map(testOptional.get(), Test_DTO.class);
        } else {
            throw new TestNotFoundException("Test not found with ID: " + testId);
        }
    }

    @Override
    @Transactional
    public List<Test_DTO> getTestByStudentSkillLevel(Integer studentId, Integer skillId, Integer levelId) {
        List<Test> tests = testRepository.findTestByStudentSkillLevel(studentId,skillId,levelId);
        return tests.stream()
                .map(test -> modelMapper.map(test, Test_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Test_DTO> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream()
                .map(test -> modelMapper.map(test, Test_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Test_DTO updateTest(Integer testId, Test_DTO updatedTest_DTO) throws TestNotFoundException {
        Optional<Test> testOptional = testRepository.findById(testId);
        if (testOptional.isPresent()) {
            Test existingTest = testOptional.get();
            // Update fields as needed
            existingTest.setStudentSkillLevel(updatedTest_DTO.getStudentSkillLevel());
            existingTest.setTestNumber(updatedTest_DTO.getTestNumber());
            existingTest.setIsPassed(updatedTest_DTO.getIsPassed());
            existingTest.setNoOfAttempts(updatedTest_DTO.getNoOfAttempts());

            Test updatedTest = testRepository.save(existingTest);
            return modelMapper.map(updatedTest, Test_DTO.class);
        } else {
            throw new TestNotFoundException("Test not found with ID: " + testId);
        }
    }

    @Override
    public boolean deleteTest(Integer testId) throws TestNotFoundException {
        Optional<Test> testOptional = testRepository.findById(testId);
        if (testOptional.isPresent()) {
            testRepository.delete(testOptional.get());
            return true;
        } else {
            throw new TestNotFoundException("Test not found with ID: " + testId);
        }
    }
}
