package com.example.SkillsetProfiling.Entity;

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
@Entity
@Table(name = "Job_Domain_Requirements")
@IdClass(Job_Domain_Requirements_Key.class)
public class Job_Domain_Requirements {
    @Id
    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job_Postings job_postings;
    @Id
    @ManyToOne
    @JoinColumn(name = "DomainID")
    private Domain domain;
}
