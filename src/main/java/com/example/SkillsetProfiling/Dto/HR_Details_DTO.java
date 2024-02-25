package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Industry;
import com.example.SkillsetProfiling.Entity.User_Details;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HR_Details_DTO {
    private Integer HrID;
    private User_Details userDetails;
    private String companyName;
    private Industry industry;
}
