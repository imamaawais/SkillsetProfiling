package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Experience;
import com.example.SkillsetProfiling.Entity.Student_Details;
import com.example.SkillsetProfiling.Key.Student_Experience_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student_Experience_DTO {
    private Student_Details studentDetails;
    private Experience experience;
    private Integer noOfYears;
}
