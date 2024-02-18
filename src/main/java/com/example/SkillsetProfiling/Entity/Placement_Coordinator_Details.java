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
@Table(name = "Placement_Coordinator_Details")
public class Placement_Coordinator_Details {

    @Id
    private Integer CoordinatorID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User_Details user_details;
}
