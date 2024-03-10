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
    @JoinColumn(name = "reportTypeID")
    private Report_Types reportTypes;
    private String reportDescription;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User_Details userDetails;
    private String reportStatus;
    private Timestamp timestampReported;
    private Timestamp timestampStatusModified;

    @PrePersist
    protected void onCreate() {
        timestampReported = new Timestamp(System.currentTimeMillis());
        timestampStatusModified = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampStatusModified = new Timestamp(System.currentTimeMillis());
    }

}
