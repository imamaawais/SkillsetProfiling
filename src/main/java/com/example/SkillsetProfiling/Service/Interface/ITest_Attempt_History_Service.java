package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Test_Attempt_History_DTO;

import java.util.List;

public interface ITest_Attempt_History_Service {
    Test_Attempt_History_DTO addTestAttemptHistory(Test_Attempt_History_DTO testAttemptHistoryDTO);

    Test_Attempt_History_DTO getTestAttemptHistoryByIds(Integer testId, Integer assessmentId);

    List<Test_Attempt_History_DTO> getAllTestAttemptHistory();

    Test_Attempt_History_DTO updateTestAttemptHistory(Integer testId, Integer assessmentId, Test_Attempt_History_DTO updatedTestAttemptHistoryDTO);

    boolean deleteTestAttemptHistory(Integer testId, Integer assessmentId);
}
