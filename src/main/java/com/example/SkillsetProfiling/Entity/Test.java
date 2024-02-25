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
@Table(name = "Test")
public class Test {

    @Id
    private Integer TestID;
    @ManyToOne
    @JoinColumn(name = "student_Skill_LevelID")
    private Student_Skill_Level studentSkillLevel;
    private Integer testNumber;
    private Boolean isPassed;
    private Integer noOfAttempts;

}
