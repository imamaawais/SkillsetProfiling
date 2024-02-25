package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Domain;
import com.example.SkillsetProfiling.Entity.Job_Postings;
import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job_Domain_Requirements_DTO {
    private Job_Postings jobPostings;
    private Domain domain;
}

