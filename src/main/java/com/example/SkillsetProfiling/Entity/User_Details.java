package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_Details")
public class User_Details {
    @Id
    private Integer UserID;
    private String email;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    @Lob
    private byte[] profile_picture;
    private Integer roleID;
    private Date date_created;
    private Date date_updated;
}
