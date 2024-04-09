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
@Table(name = "Feedback_Response")
public class Feedback_Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer FeedbackResponseID;
    @OneToOne
    @JoinColumn(name = "FeedbackID")
    private Feedback_Request feedbackID;
    @ManyToOne
    @JoinColumn(name = "mentorID")
    private Mentor_Details mentorDetails;
    private String responseMessage;
    private Timestamp responseTimestamp;

    @PrePersist
    protected void onCreate() {
        responseTimestamp = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        responseTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
