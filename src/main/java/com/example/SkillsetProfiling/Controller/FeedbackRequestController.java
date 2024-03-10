package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Feedback_Request_DTO;
import com.example.SkillsetProfiling.Exception.DuplicateFeedbackRequestException;
import com.example.SkillsetProfiling.Exception.FeedbackRequestNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Feedback_Request_Service;
import com.example.SkillsetProfiling.Service.Interface.IFeedback_Request_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/feedback_request")
public class FeedbackRequestController {

    private Feedback_Request_Service feedbackRequestService;

    @PostMapping("/add")
    public ResponseEntity<Feedback_Request_DTO> addFeedbackRequest(@RequestBody Feedback_Request_DTO feedbackRequestDTO) {
        try {
            Feedback_Request_DTO savedFeedbackRequest = feedbackRequestService.addFeedbackRequest(feedbackRequestDTO);
            return new ResponseEntity<>(savedFeedbackRequest, HttpStatus.CREATED);
        } catch (DuplicateFeedbackRequestException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Feedback_Request_DTO>> getAllFeedbackRequests() {
        List<Feedback_Request_DTO> feedbackRequests = feedbackRequestService.getAllFeedbackRequests();

        if (feedbackRequests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(feedbackRequests, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{feedbackId}")
    public ResponseEntity<Feedback_Request_DTO> getFeedbackRequestById(@PathVariable Integer feedbackId) {
        try {
            Feedback_Request_DTO feedbackRequestDTO = feedbackRequestService.getFeedbackRequestById(feedbackId);
            return new ResponseEntity<>(feedbackRequestDTO, HttpStatus.OK);
        } catch (FeedbackRequestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{feedbackId}")
    public ResponseEntity<Feedback_Request_DTO> updateFeedbackRequest(@PathVariable Integer feedbackId, @RequestBody Feedback_Request_DTO updatedFeedbackRequestDTO) {
        try {
            Feedback_Request_DTO updatedFeedbackRequest = feedbackRequestService.updateFeedbackRequest(feedbackId, updatedFeedbackRequestDTO);
            return new ResponseEntity<>(updatedFeedbackRequest, HttpStatus.OK);
        } catch (FeedbackRequestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<Void> deleteFeedbackRequest(@PathVariable Integer feedbackId) {
        try {
            boolean deleted = feedbackRequestService.deleteFeedbackRequest(feedbackId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (FeedbackRequestNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
