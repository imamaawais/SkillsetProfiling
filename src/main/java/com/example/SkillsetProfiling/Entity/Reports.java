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
@Table(name = "Reports")
public class Reports {

    @Id
    private Integer ReportID;
    @ManyToOne
    @JoinColumn(name = "report_TypeID")
    private Report_Types report_types;
    private String reportDescription;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User_Details user_details;
    private String reportStatus;
    private Timestamp timestampReported;
    private Timestamp timestampStatusModified;
}
