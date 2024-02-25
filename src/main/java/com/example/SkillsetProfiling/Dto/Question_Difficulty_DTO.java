package com.example.SkillsetProfiling.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question_Difficulty_DTO {
    private Integer DifficultyID;
    private Integer marks;
    private Integer timeAllotted; //in minutes
}
