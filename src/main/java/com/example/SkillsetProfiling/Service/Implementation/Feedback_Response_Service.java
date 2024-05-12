package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Feedback_Response_DTO;
import com.example.SkillsetProfiling.Entity.Feedback_Response;
import com.example.SkillsetProfiling.Exception.DuplicateFeedbackResponseException;
import com.example.SkillsetProfiling.Exception.FeedbackResponseNotFoundException;
import com.example.SkillsetProfiling.Repository.Feedback_Response_Repo;
import com.example.SkillsetProfiling.Service.Interface.IFeedback_Response_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Feedback_Response_Service implements IFeedback_Response_Service {

    private final Feedback_Response_Repo feedbackResponseRepo;
    private final ModelMapper modelMapper;

    @Override
    public Feedback_Response_DTO addFeedbackResponse(Feedback_Response_DTO feedbackResponseDTO) {
        Feedback_Response feedbackResponse = modelMapper.map(feedbackResponseDTO, Feedback_Response.class);

        Feedback_Response savedFeedbackResponse = feedbackResponseRepo.save(feedbackResponse);
        return modelMapper.map(savedFeedbackResponse, Feedback_Response_DTO.class);
    }

    @Override
    public Feedback_Response_DTO getFeedbackResponseById(Integer responseId) throws FeedbackResponseNotFoundException {
        Optional<Feedback_Response> feedbackResponseOptional = feedbackResponseRepo.findById(responseId);
        if (feedbackResponseOptional.isPresent()) {
            return modelMapper.map(feedbackResponseOptional.get(), Feedback_Response_DTO.class);
        } else {
            throw new FeedbackResponseNotFoundException("Feedback response not found with ID: " + responseId);
        }
    }

    @Override
    @Transactional
    public Feedback_Response_DTO getFeedbackResponseByFeedbackId(Integer feedbackId) throws FeedbackResponseNotFoundException {
        Optional<Feedback_Response> feedbackResponseOptional = feedbackResponseRepo.findByFeedbackID_FeedbackID(feedbackId);
        if (feedbackResponseOptional.isPresent()) {
            return modelMapper.map(feedbackResponseOptional.get(), Feedback_Response_DTO.class);
        } else {
            throw new FeedbackResponseNotFoundException("Feedback response not found with ID: " + feedbackId);
        }
    }

    @Override
    public List<Feedback_Response_DTO> getAllFeedbackResponses() {
        List<Feedback_Response> feedbackResponses = feedbackResponseRepo.findAll();
        return feedbackResponses.stream()
                .map(response -> modelMapper.map(response, Feedback_Response_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Feedback_Response_DTO> getAllFeedbackResponsesByStudentID(Integer studentID) {
        List<Feedback_Response> feedbackResponses = feedbackResponseRepo.getAllFeedbackResponsesByStudentID(studentID);
        return feedbackResponses.stream()
                .map(response -> modelMapper.map(response, Feedback_Response_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Feedback_Response_DTO updateFeedbackResponse(Integer feedbackId, Feedback_Response_DTO updatedFeedbackResponseDTO) throws FeedbackResponseNotFoundException {
        Optional<Feedback_Response> feedbackResponseOptional = feedbackResponseRepo.findById(feedbackId);
        if (feedbackResponseOptional.isPresent()) {
            Feedback_Response existingFeedbackResponse = feedbackResponseOptional.get();
            existingFeedbackResponse.setResponseMessage(updatedFeedbackResponseDTO.getResponseMessage());
            existingFeedbackResponse.setResponseTimestamp(updatedFeedbackResponseDTO.getResponseTimestamp());
            existingFeedbackResponse.setMentorDetails(updatedFeedbackResponseDTO.getMentorDetails());
            Feedback_Response updatedFeedbackResponse = feedbackResponseRepo.save(existingFeedbackResponse);
            return modelMapper.map(updatedFeedbackResponse, Feedback_Response_DTO.class);
        } else {
            throw new FeedbackResponseNotFoundException("Feedback response not found with ID: " + feedbackId);
        }
    }

    @Override
    public boolean deleteFeedbackResponse(Integer feedbackId) throws FeedbackResponseNotFoundException {
        Optional<Feedback_Response> feedbackResponseOptional = feedbackResponseRepo.findById(feedbackId);
        if (feedbackResponseOptional.isPresent()) {
            feedbackResponseRepo.delete(feedbackResponseOptional.get());
            return true;
        } else {
            throw new FeedbackResponseNotFoundException("Feedback response not found with ID: " + feedbackId);
        }
    }
}
