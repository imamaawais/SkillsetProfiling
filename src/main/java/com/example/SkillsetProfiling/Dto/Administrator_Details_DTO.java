package com.example.SkillsetProfiling.Dto;
import com.example.SkillsetProfiling.Entity.User_Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrator_Details_DTO {
    private Integer AdministratorID;
    private User_Details user_details;
}

