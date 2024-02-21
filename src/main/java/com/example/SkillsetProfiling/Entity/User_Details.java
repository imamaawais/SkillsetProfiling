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
    @OneToOne
    @JoinColumn(name = "email")
    private Auth auth;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @Lob
    private byte[] profilePicture;
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;
    private Timestamp timestampCreated;
    private Timestamp timestampUpdated;
}
