package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reports")
public class Reports {

    @Id
    private Integer ReportID;
    @ManyToOne
    @JoinColumn(name = "reportTypeID")
    private Report_Types report_types;
    private String report_description;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User_Details user_details;
    private String report_status;
    private Timestamp timestamp_reported;
    private Timestamp timestamp_status_modified;
}
