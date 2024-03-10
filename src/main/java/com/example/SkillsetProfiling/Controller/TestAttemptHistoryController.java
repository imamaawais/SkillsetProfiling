package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Test_Attempt_History_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateTestAttemptHistoryException;
import com.example.SkillsetProfiling.Exception.TestAttemptHistoryNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Test_Attempt_History_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/test_attempt_history")
public class TestAttemptHistoryController {

    private Test_Attempt_History_Service testAttemptHistoryService;

    @PostMapping("/add")
    public ResponseEntity<Test_Attempt_History_DTO> addTestAttemptHistory(@RequestBody Test_Attempt_History_DTO testAttemptHistoryDTO) {
        try {
            Test_Attempt_History_DTO savedTestAttemptHistory = testAttemptHistoryService.addTestAttemptHistory(testAttemptHistoryDTO);
            return new ResponseEntity<>(savedTestAttemptHistory, HttpStatus.CREATED);
        } catch (DuplicateTestAttemptHistoryException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Test_Attempt_History_DTO>> getAllTestAttemptHistory() {
        List<Test_Attempt_History_DTO> testAttemptHistoryList = testAttemptHistoryService.getAllTestAttemptHistory();
        if (testAttemptHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testAttemptHistoryList, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{testId}/{assessmentId}")
    public ResponseEntity<Test_Attempt_History_DTO> getTestAttemptHistoryByIds(@PathVariable Integer testId, @PathVariable Integer assessmentId) {
        try {
            Test_Attempt_History_DTO testAttemptHistoryDTO = testAttemptHistoryService.getTestAttemptHistoryByIds(testId, assessmentId);
            return new ResponseEntity<>(testAttemptHistoryDTO, HttpStatus.OK);
        } catch (TestAttemptHistoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{testId}/{assessmentId}")
    public ResponseEntity<Test_Attempt_History_DTO> updateTestAttemptHistory(@PathVariable Integer testId, @PathVariable Integer assessmentId, @RequestBody Test_Attempt_History_DTO updatedTestAttemptHistoryDTO) {
        try {
            Test_Attempt_History_DTO updatedTestAttemptHistory = testAttemptHistoryService.updateTestAttemptHistory(testId, assessmentId, updatedTestAttemptHistoryDTO);
            return new ResponseEntity<>(updatedTestAttemptHistory, HttpStatus.OK);
        } catch (TestAttemptHistoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{testId}/{assessmentId}")
    public ResponseEntity<Void> deleteTestAttemptHistory(@PathVariable Integer testId, @PathVariable Integer assessmentId) {
        try {
            boolean deleted = testAttemptHistoryService.deleteTestAttemptHistory(testId, assessmentId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (TestAttemptHistoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
