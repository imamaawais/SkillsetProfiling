package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Job_Postings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Job_Postings_Repo extends JpaRepository<Job_Postings, Integer> {
    @Query("SELECT jp FROM Job_Postings jp WHERE jp.hrDetails.HrID = :HrID")

    Optional<List<Job_Postings>> findAllByHrDetails_HrID(@Param("HrID") Integer HrID);
}
