package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Domain;
import com.example.SkillsetProfiling.Entity.User_Details;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student_Details_DTO {
    private Integer StudentID;
    private User_Details userDetails;
    private Integer enrollmentYear;
    private Domain domain;
    private Integer expectedGraduation;
    @Lob
    private byte[] resume;
}
