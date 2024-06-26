package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Feedback_Response_DTO;

import java.util.List;

public interface IFeedback_Response_Service {
    Feedback_Response_DTO addFeedbackResponse(Feedback_Response_DTO feedbackResponseDTO);

    Feedback_Response_DTO getFeedbackResponseById(Integer feedbackId);

    Feedback_Response_DTO getFeedbackResponseByFeedbackId(Integer feedbackId);

    List<Feedback_Response_DTO> getAllFeedbackResponses();

    List<Feedback_Response_DTO> getAllFeedbackResponsesByStudentID(Integer studentID);

    Feedback_Response_DTO updateFeedbackResponse(Integer feedbackId, Feedback_Response_DTO updatedFeedbackResponseDTO);

    boolean deleteFeedbackResponse(Integer feedbackId);
}
