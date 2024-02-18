package com.example.SkillsetProfiling.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assessment_Scores_Key implements Serializable {
    private Integer AssessmentID;
    private Integer QuestionID;
}
