package com.example.SkillsetProfiling.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer time_allotted; //in minutes
}
