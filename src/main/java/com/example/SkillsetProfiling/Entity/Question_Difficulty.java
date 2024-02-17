package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question_Difficulty")
public class Question_Difficulty {

    @Id
    private Integer DifficultyID;
    private Integer marks;
    private Integer time_allotted; //in minutes
}
