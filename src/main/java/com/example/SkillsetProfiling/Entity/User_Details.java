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
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
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


    @PrePersist
    protected void onCreate() {
        timestampCreated = new Timestamp(System.currentTimeMillis());
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        timestampUpdated = new Timestamp(System.currentTimeMillis());
    }

}
