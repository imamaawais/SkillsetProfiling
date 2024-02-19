package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.Report_Types;
import com.example.SkillsetProfiling.Entity.User_Details;
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
public class Reports_DTO {
    private Integer ReportID;
    private Report_Types report_types;
    private String report_description;
    private User_Details user_details;
    private String report_status;
    private Timestamp timestamp_reported;
    private Timestamp timestamp_status_modified;
}
