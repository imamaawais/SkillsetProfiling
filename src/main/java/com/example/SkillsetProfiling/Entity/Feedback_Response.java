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
    private Integer FeedbackID;
    @ManyToOne
    @JoinColumn(name = "mentorID")
    private Mentor_Details mentor_details;
    private String responseMessage;
    private Timestamp responseTimestamp;
}
