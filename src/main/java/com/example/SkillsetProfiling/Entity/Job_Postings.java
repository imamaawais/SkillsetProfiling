package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer hrID;
    private String job_title;
    private String job_description;
    private Boolean required_industrial_experience;
    private String posting_status;
    private Date date_created;
    private Date date_closed;
}
