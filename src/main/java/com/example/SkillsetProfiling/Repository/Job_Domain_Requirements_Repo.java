package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Job_Domain_Requirements;
import com.example.SkillsetProfiling.Key.Job_Domain_Requirements_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Job_Domain_Requirements_Repo extends JpaRepository<Job_Domain_Requirements, Job_Domain_Requirements_Key> {
}
