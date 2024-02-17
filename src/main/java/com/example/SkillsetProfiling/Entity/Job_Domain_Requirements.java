package com.example.SkillsetProfiling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Job_Domain_Requirements")
public class Job_Domain_Requirements {
    @Id
    private Integer JobID;
    @Id
    private Integer DomainID;
}
