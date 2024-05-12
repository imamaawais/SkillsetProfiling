package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Feedback_Request;
import com.example.SkillsetProfiling.Entity.Feedback_Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Feedback_Response_Repo extends JpaRepository<Feedback_Response, Integer> {
    @Query("SELECT f FROM Feedback_Response f WHERE f.feedbackID.FeedbackID = :FeedbackID")
    Optional<Feedback_Response> findByFeedbackID_FeedbackID(Integer FeedbackID);
    @Query("SELECT f FROM Feedback_Response f WHERE f.feedbackID.studentDetails.StudentID = :studentID")
    List<Feedback_Response> getAllFeedbackResponsesByStudentID(Integer studentID);
}
