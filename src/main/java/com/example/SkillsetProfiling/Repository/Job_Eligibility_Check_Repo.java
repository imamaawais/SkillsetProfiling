package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Job_Eligibility_Check;
import com.example.SkillsetProfiling.Key.Job_Eligibility_Check_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Job_Eligibility_Check_Repo extends JpaRepository<Job_Eligibility_Check, Job_Eligibility_Check_Key> {
    @Query("SELECT jec FROM Job_Eligibility_Check jec WHERE jec.JobID.JobID = :JobID")
    List<Job_Eligibility_Check> getJobEligibilityChecksByJobID(@Param("JobID") Integer JobID);
}
