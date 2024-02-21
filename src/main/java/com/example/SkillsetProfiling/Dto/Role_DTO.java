package com.example.SkillsetProfiling.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role_DTO {
    private Integer RoleID;
    //@JsonProperty("role_name") // dont need since object mapper used
    private String roleName;
}
