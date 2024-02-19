package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.HR_Details;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job_Postings_DTO {
    private Integer JobID;
    private HR_Details hr_details;
    private String job_title;
    private String job_description;
    private Boolean required_industrial_experience;
    private String posting_status;
    private Timestamp timestamp_created;
    private Timestamp timestamp_closed;
}

