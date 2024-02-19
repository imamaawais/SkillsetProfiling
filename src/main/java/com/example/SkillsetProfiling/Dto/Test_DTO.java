package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Student_Skill_Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test_DTO {
    private Integer TestID;
    private Student_Skill_Level student_skill_level;
    private Integer test_number;
    private Boolean is_passed;
    private Integer no_of_attempts;

}
