package com.example.SkillsetProfiling.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth_DTO {
    private String email;
    private String password;
    private String phoneNumber;
}
