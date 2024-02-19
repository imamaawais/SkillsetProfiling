package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import com.example.SkillsetProfiling.Entity.Student_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback_Request_DTO {
    private Integer FeedbackID;
    private Assessment assessment;
    private Question_Bank question_bank;
    private Student_Details student_details;
    private String request_message;
    private Timestamp request_timestamp;
    private String request_status;
}
