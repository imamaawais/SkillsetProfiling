package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Job_Skills_Requirements;
import com.example.SkillsetProfiling.Key.Job_Skills_Requirements_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Job_Skills_Requirements_Repo extends JpaRepository<Job_Skills_Requirements, Job_Skills_Requirements_Key> {
}
