package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserID;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "email")
    private Auth auth;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @Lob
    private byte[] profilePicture;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
