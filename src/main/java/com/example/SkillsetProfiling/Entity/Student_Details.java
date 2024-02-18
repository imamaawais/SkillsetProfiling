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
@Table(name = "Student_Details")
public class Student_Details {
    @Id
    private Integer StudentID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User_Details userID;
    private Integer enrollment_year;
    @ManyToOne
    @JoinColumn(name = "domainID")
    private Domain domainID;
    private Integer expected_graduation;
}
