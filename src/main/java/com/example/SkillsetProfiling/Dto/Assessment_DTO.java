package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Question_Paper;
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
public class Assessment_DTO {
    private Integer AssessmentID;
    private Question_Paper questionPaper;
    private Student_Details studentDetails;
    private Integer timeTaken;
    private Integer totalScore;
    private Timestamp assessmentTimestamp;
    private Boolean isPassed;
}
