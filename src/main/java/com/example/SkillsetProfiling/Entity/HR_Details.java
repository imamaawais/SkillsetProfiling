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
@Table(name = "HR_Details")
public class HR_Details {
    @Id
    private Integer HrID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User_Details userDetails;
    private String companyName;
    @ManyToOne
    @JoinColumn(name = "industryID")
    private Industry industry;
}
