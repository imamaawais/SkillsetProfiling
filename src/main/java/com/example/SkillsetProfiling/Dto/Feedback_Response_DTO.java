package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Mentor_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback_Response_DTO {
    private Integer FeedbackID;
    private Mentor_Details mentorDetails;
    private String responseMessage;
    private Timestamp responseTimestamp;
}
