package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Feedback_Request_DTO;

import java.util.List;

public interface IFeedback_Request_Service {
    Feedback_Request_DTO addFeedbackRequest(Feedback_Request_DTO feedbackRequestDTO);

    Feedback_Request_DTO getFeedbackRequestById(Integer feedbackId);

    List<Feedback_Request_DTO> getAllFeedbackRequests();

    Feedback_Request_DTO updateFeedbackRequest(Integer feedbackId, Feedback_Request_DTO updatedFeedbackRequestDTO);

    boolean deleteFeedbackRequest(Integer feedbackId);
}
