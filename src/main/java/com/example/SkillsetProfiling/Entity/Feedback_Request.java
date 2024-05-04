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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer FeedbackID;
    @ManyToOne
    @JoinColumn(name = "assessmentID")
    private Assessment assessment;
    @ManyToOne
    @JoinColumn(name = "questionID")
    private Question_Bank questionBank;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student_Details studentDetails;
    private String requestMessage;
    private Timestamp requestTimestamp;
    private String requestStatus;

    @PrePersist
    protected void onCreate() {
        requestTimestamp = new Timestamp(System.currentTimeMillis());
        requestStatus = "Pending";
    }

    @PreUpdate
    protected void onUpdate() {
        requestTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
