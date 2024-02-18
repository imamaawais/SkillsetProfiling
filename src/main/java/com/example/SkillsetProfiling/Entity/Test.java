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
    @JoinColumn(name = "studentSkillLevelID")
    private Student_Skill_Level student_skill_level;
    private Integer test_number;
    private Boolean is_passed;
    private Integer no_of_attempts;

}
