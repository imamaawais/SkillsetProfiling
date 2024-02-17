package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Integer reportTypeID;
    private String report_description;
    private Integer userID;
    private String report_status;
    private Timestamp timestamp_reported;
    private Timestamp timestamp_status_modified;
}
