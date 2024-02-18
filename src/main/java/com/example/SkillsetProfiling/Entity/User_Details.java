package com.example.SkillsetProfiling.Entity;

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
@Entity
@Table(name = "User_Details")
public class User_Details {
    @Id
    private Integer UserID;
    @ManyToOne
    @JoinColumn(name = "email")
    private Auth email;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    @Lob
    private byte[] profile_picture;
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role roleID;
    private Timestamp timestamp_created;
    private Timestamp timestamp_updated;
}
