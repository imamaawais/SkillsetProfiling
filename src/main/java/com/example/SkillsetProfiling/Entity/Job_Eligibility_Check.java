package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Job_Eligibility_Check_Key;
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
@Table(name = "Job_Eligibility_Check")
@IdClass(Job_Eligibility_Check_Key.class)
public class Job_Eligibility_Check {
    @Id
    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job_Postings JobID;
    @Id
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student_Details StudentID;
    private Timestamp timestampChecked;
    private Boolean eligible;
    private Boolean applied;
    private Timestamp timestampApplied;

    @PrePersist
    protected void onCreate() {
        timestampChecked = new Timestamp(System.currentTimeMillis());
        timestampApplied = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampApplied = new Timestamp(System.currentTimeMillis());
    }

}
