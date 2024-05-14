package com.example.SkillsetProfiling.Entity;

import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Job_Domain_Requirements")
@IdClass(Job_Domain_Requirements_Key.class)
public class Job_Domain_Requirements {
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "JobID")
    private Job_Postings JobID;
    @Id
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "DomainID")
    private Domain DomainID;
}
