package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.HR_Details;
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
    private HR_Details hrDetails;
    private String jobTitle;
    private String jobDescription;
    private Boolean requiredIndustrialExperience;
    private String postingStatus;
    private Timestamp timestampCreated;
    private Timestamp timestampClosed;
}

