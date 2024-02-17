package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Integer assessmentID;
    private Integer questionID;
    private String request_message;
    private Timestamp request_timestamp;
    private String request_status;
}
