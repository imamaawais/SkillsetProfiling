package com.example.SkillsetProfiling.Entity;

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
@Entity
@Table(name = "Job_Postings")
public class Job_Postings {
    @Id
    private Integer JobID;
    @ManyToOne
    @JoinColumn(name = "hrID")
    private HR_Details hrDetails;
    private String jobTitle;
    private String jobDescription;
    private Boolean requiredIndustrialExperience;
    private String postingStatus;
    private Timestamp timestampCreated;
    private Timestamp timestampClosed;
}
