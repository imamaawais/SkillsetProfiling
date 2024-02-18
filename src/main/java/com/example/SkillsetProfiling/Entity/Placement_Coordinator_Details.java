package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Integer userID;
}