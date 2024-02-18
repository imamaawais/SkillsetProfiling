package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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
    private HR_Details hr_details;
    private String job_title;
    private String job_description;
    private Boolean required_industrial_experience;
    private String posting_status;
    private Timestamp timestamp_created;
    private Timestamp timestamp_closed;
}
