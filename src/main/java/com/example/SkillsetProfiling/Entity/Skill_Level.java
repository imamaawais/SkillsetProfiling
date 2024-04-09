package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Skill_Level")
public class Skill_Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer LevelID;
    private String levelName;
    private Integer noTestsRequired;
}
