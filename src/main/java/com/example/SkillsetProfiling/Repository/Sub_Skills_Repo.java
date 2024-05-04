package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Student_Skills;
import com.example.SkillsetProfiling.Entity.Sub_Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Sub_Skills_Repo extends JpaRepository<Sub_Skills, Integer> {
    @Query("SELECT ss FROM Sub_Skills ss WHERE ss.skills.SkillID = :SkillID")
    List<Sub_Skills> findBySkillID(@Param("SkillID") Integer SkillID);
}
