package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Badge_Assignment;
import com.example.SkillsetProfiling.Key.Badge_Assignment_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Badge_Assignment_Repo extends JpaRepository<Badge_Assignment, Badge_Assignment_Key> {
}
