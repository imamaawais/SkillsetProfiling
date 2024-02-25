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
    private Question_Bank questionBank;
    private Student_Details studentDetails;
    private String requestMessage;
    private Timestamp requestTimestamp;
    private String requestStatus;
}
