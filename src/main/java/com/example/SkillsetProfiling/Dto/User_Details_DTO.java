package com.example.SkillsetProfiling.Dto;

import com.example.SkillsetProfiling.Entity.Auth;
import com.example.SkillsetProfiling.Entity.Role;
import jakarta.persistence.*;
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
public class User_Details_DTO {
    private Integer UserID;
    private Auth auth;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    @Lob
    private byte[] profile_picture;
    private Role role;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;
}
