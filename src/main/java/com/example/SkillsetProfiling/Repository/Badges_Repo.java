package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Badge_Groups;
import com.example.SkillsetProfiling.Entity.Badges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Badges_Repo extends JpaRepository<Badges, Integer> {
}
