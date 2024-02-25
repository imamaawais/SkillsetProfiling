package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.User_Details;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Placement_Coordinator_Details_DTO {
    private Integer CoordinatorID;
    private User_Details userDetails;
}
