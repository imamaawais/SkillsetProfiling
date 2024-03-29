package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Test_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateTestException;
import com.example.SkillsetProfiling.Exception.TestNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Test_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/test")
public class TestController {

    private Test_Service testService;

    @PostMapping("/add")
    public ResponseEntity<Test_DTO> addTest(@RequestBody Test_DTO testDTO) {
        try {
            Test_DTO savedTest = testService.addTest(testDTO);
            return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
        } catch (DuplicateTestException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Test_DTO>> getAllTests() {
        List<Test_DTO> tests = testService.getAllTests();
        if (tests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{testId}")
    public ResponseEntity<Test_DTO> getTestById(@PathVariable Integer testId) {
        try {
            Test_DTO testDTO = testService.getTestById(testId);
            return new ResponseEntity<>(testDTO, HttpStatus.OK);
        } catch (TestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{testId}")
    public ResponseEntity<Test_DTO> updateTest(@PathVariable Integer testId, @RequestBody Test_DTO updatedTest_DTO) {
        try {
            Test_DTO updatedTest = testService.updateTest(testId, updatedTest_DTO);
            return new ResponseEntity<>(updatedTest, HttpStatus.OK);
        } catch (TestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{testId}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer testId) {
        try {
            boolean deleted = testService.deleteTest(testId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (TestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
