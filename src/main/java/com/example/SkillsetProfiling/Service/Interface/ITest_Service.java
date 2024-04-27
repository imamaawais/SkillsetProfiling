package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Test_DTO;
import com.example.SkillsetProfiling.Exception.TestNotFoundException;

import java.util.List;

public interface ITest_Service {
    Test_DTO addTest(Test_DTO testDTO);

    Test_DTO getTestById(Integer testId) throws TestNotFoundException;

    List<Test_DTO> getTestByStudentSkillLevel(Integer studentId, Integer skillId, Integer levelId);

    List<Test_DTO> getAllTests();

    Test_DTO updateTest(Integer testId, Test_DTO updatedTest_DTO) throws TestNotFoundException;

    boolean deleteTest(Integer testId) throws TestNotFoundException;
}
