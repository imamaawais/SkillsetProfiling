package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Feedback_Request_DTO;
import com.example.SkillsetProfiling.Entity.Feedback_Request;
import com.example.SkillsetProfiling.Exception.DuplicateFeedbackRequestException;
import com.example.SkillsetProfiling.Exception.FeedbackRequestNotFoundException;
import com.example.SkillsetProfiling.Repository.Feedback_Request_Repo;
import com.example.SkillsetProfiling.Service.Interface.IFeedback_Request_Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Feedback_Request_Service implements IFeedback_Request_Service {

    private final Feedback_Request_Repo feedbackRequestRepo;
    private final ModelMapper modelMapper;

    public Feedback_Request_Service(Feedback_Request_Repo feedbackRequestRepo, ModelMapper modelMapper) {
        this.feedbackRequestRepo = feedbackRequestRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Feedback_Request_DTO addFeedbackRequest(Feedback_Request_DTO feedbackRequestDTO) {
        Feedback_Request feedbackRequest = modelMapper.map(feedbackRequestDTO, Feedback_Request.class);
        if (feedbackRequestRepo.findById(feedbackRequest.getFeedbackID()).isPresent()) {
            throw new DuplicateFeedbackRequestException("Feedback request already exists with ID: " + feedbackRequest.getFeedbackID());
        }
        Feedback_Request savedFeedbackRequest = feedbackRequestRepo.save(feedbackRequest);
        return modelMapper.map(savedFeedbackRequest, Feedback_Request_DTO.class);
    }

    @Override
    public Feedback_Request_DTO getFeedbackRequestById(Integer feedbackId) throws FeedbackRequestNotFoundException {
        Optional<Feedback_Request> feedbackRequestOptional = feedbackRequestRepo.findById(feedbackId);
        if (feedbackRequestOptional.isPresent()) {
            return modelMapper.map(feedbackRequestOptional.get(), Feedback_Request_DTO.class);
        } else {
            throw new FeedbackRequestNotFoundException("Feedback request not found with ID: " + feedbackId);
        }
    }

    @Override
    public List<Feedback_Request_DTO> getAllFeedbackRequests() {
        List<Feedback_Request> feedbackRequests = feedbackRequestRepo.findAll();
        return feedbackRequests.stream()
                .map(request -> modelMapper.map(request, Feedback_Request_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Feedback_Request_DTO updateFeedbackRequest(Integer feedbackId, Feedback_Request_DTO updatedFeedbackRequestDTO) throws FeedbackRequestNotFoundException {
        Optional<Feedback_Request> feedbackRequestOptional = feedbackRequestRepo.findById(feedbackId);
        if (feedbackRequestOptional.isPresent()) {
            Feedback_Request existingFeedbackRequest = feedbackRequestOptional.get();
            existingFeedbackRequest.setRequestMessage(updatedFeedbackRequestDTO.getRequestMessage());
            existingFeedbackRequest.setRequestTimestamp(updatedFeedbackRequestDTO.getRequestTimestamp());
            existingFeedbackRequest.setRequestStatus(updatedFeedbackRequestDTO.getRequestStatus());
            existingFeedbackRequest.setQuestionBank(updatedFeedbackRequestDTO.getQuestionBank());
            existingFeedbackRequest.setAssessment(updatedFeedbackRequestDTO.getAssessment());
            existingFeedbackRequest.setStudentDetails(updatedFeedbackRequestDTO.getStudentDetails());
            Feedback_Request updatedFeedbackRequest = feedbackRequestRepo.save(existingFeedbackRequest);
            return modelMapper.map(updatedFeedbackRequest, Feedback_Request_DTO.class);
        } else {
            throw new FeedbackRequestNotFoundException("Feedback request not found with ID: " + feedbackId);
        }
    }

    @Override
    public boolean deleteFeedbackRequest(Integer feedbackId) throws FeedbackRequestNotFoundException {
        Optional<Feedback_Request> feedbackRequestOptional = feedbackRequestRepo.findById(feedbackId);
        if (feedbackRequestOptional.isPresent()) {
            feedbackRequestRepo.delete(feedbackRequestOptional.get());
            return true;
        } else {
            throw new FeedbackRequestNotFoundException("Feedback request not found with ID: " + feedbackId);
        }
    }
}
