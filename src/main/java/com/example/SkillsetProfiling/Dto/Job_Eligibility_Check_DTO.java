package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Job_Postings;
import com.example.SkillsetProfiling.Entity.Student_Details;
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
public class Job_Eligibility_Check_DTO {
    private Job_Postings jobPosting;
    private Student_Details studentDetails;
    private Timestamp timestampChecked;
    private Boolean eligible;
    private Boolean applied;
    private Timestamp timestampApplied;
}

