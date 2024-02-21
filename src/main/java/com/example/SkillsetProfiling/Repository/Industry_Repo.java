package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Industry_Repo extends JpaRepository<Industry, Integer> {
    Optional<Industry> findByIndustryName(String industryName);
}
