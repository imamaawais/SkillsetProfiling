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
    private Question_Paper question_paper;
    private Student_Details student_details;
    private Integer time_taken;
    private Integer total_score;
    private Timestamp assessment_timestamp;
    private Boolean is_passed;
}
