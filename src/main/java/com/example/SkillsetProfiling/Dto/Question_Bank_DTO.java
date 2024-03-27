package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Question_Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question_Bank_DTO {
    private Integer QuestionID;
    private String questionText;
    private String answer;
    private Question_Difficulty questionDifficulty;
    private Timestamp timestampCreated;
    private Timestamp timestampUpdated;
    boolean isMCQ;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
