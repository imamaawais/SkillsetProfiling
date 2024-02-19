package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Badge_Assignment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Badge_Assignment_Repo extends JpaRepository<Badge_Assignment, Integer> {
}
