package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DifficultyID;
    private Integer marks;
    private Integer timeAllotted; //in minutes
}
