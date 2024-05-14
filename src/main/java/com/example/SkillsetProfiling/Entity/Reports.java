package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reports")
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ReportID;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "report_TypeID")
    private Report_Types reportTypes;
    private String reportDescription;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
