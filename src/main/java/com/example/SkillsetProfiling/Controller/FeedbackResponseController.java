package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Feedback_Response_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateFeedbackResponseException;
import com.example.SkillsetProfiling.Exception.FeedbackResponseNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Feedback_Response_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/feedback_response")
public class FeedbackResponseController {

    private final Feedback_Response_Service feedbackResponseService;

    @PostMapping("/add")
    public ResponseEntity<Feedback_Response_DTO> addFeedbackResponse(@RequestBody Feedback_Response_DTO feedbackResponseDTO) {
        try {
            Feedback_Response_DTO savedFeedbackResponse = feedbackResponseService.addFeedbackResponse(feedbackResponseDTO);
            return new ResponseEntity<>(savedFeedbackResponse, HttpStatus.CREATED);
        } catch (DuplicateFeedbackResponseException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Feedback_Response_DTO>> getAllFeedbackResponses() {
        List<Feedback_Response_DTO> feedbackResponses = feedbackResponseService.getAllFeedbackResponses();

        if (feedbackResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(feedbackResponses, HttpStatus.OK);
    }

    @GetMapping("/getById/{feedbackId}")
    public ResponseEntity<Feedback_Response_DTO> getFeedbackResponseById(@PathVariable Integer feedbackId) {
        try {
            Feedback_Response_DTO feedbackResponseDTO = feedbackResponseService.getFeedbackResponseById(feedbackId);
            return new ResponseEntity<>(feedbackResponseDTO, HttpStatus.OK);
        } catch (FeedbackResponseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{feedbackId}")
    public ResponseEntity<Feedback_Response_DTO> updateFeedbackResponse(@PathVariable Integer feedbackId, @RequestBody Feedback_Response_DTO updatedFeedbackResponseDTO) {
        try {
            Feedback_Response_DTO updatedFeedbackResponse = feedbackResponseService.updateFeedbackResponse(feedbackId, updatedFeedbackResponseDTO);
            return new ResponseEntity<>(updatedFeedbackResponse, HttpStatus.OK);
        } catch (FeedbackResponseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<Void> deleteFeedbackResponse(@PathVariable Integer feedbackId) {
        try {
            boolean deleted = feedbackResponseService.deleteFeedbackResponse(feedbackId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (FeedbackResponseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
