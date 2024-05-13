package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Feedback_Response;
import com.example.SkillsetProfiling.Entity.Job_Skills_Requirements;
import com.example.SkillsetProfiling.Key.Job_Skills_Requirements_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Job_Skills_Requirements_Repo extends JpaRepository<Job_Skills_Requirements, Job_Skills_Requirements_Key> {
    @Query("SELECT jsr FROM Job_Skills_Requirements jsr WHERE jsr.JobID.JobID = :jobID")
    List<Job_Skills_Requirements> getJobSkillsRequirementsByJobID(Integer jobID);
}
