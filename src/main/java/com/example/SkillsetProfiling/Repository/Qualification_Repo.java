package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Qualification_Repo extends JpaRepository<Qualification, Integer> {
    Optional<Qualification> findByQualificationName(String qualificationName);
}
