package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "Job_Eligibility_Check")
public class Job_Eligibility_Check {
    @Id
    private Integer JobID;
    @Id
    private Integer StudentID;
    private Timestamp timestamp_checked;
    private Boolean eligible;
    private Boolean applied;
    private Timestamp timestamp_applied;
}