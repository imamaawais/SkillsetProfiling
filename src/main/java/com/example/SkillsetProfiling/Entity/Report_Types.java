package com.example.SkillsetProfiling.Entity;
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
@Table(name = "Report_Types")
public class Report_Types {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ReportTypeID;
    private String typeName;
}
