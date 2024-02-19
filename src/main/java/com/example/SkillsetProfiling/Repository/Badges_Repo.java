package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Badge_Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Badges_Repo extends JpaRepository<Badge_Groups, Integer> {
}
