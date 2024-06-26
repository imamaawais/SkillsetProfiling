package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Assessment;
import com.example.SkillsetProfiling.Entity.Question_Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assessment_Scores_DTO {
    private Assessment assessment;
    private Question_Bank questionBank;
    private String userAnswer;
    private Boolean isAttempted;
}
