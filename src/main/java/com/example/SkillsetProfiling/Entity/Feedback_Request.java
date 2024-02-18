package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Feedback_Request")
public class Feedback_Request {

    @Id
    private Integer FeedbackID;
    @ManyToOne
    @JoinColumn(name = "assessmentID")
    private Assessment assessment;
    @ManyToOne
    @JoinColumn(name = "questionID")
    private Question_Bank question_bank;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student_Details student_details;
    private String request_message;
    private Timestamp request_timestamp;
    private String request_status;
}
