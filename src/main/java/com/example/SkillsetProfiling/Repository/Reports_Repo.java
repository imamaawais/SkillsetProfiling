package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Reports_Repo extends JpaRepository<Reports, Integer> {
    @Query("SELECT r FROM Reports r WHERE r.reportStatus = 'Pending'")
    List<Reports> getAllPendingCases();
    @Query("SELECT r FROM Reports r WHERE r.reportStatus != 'Pending'")
    List<Reports> getAllNotPendingCases();
}
