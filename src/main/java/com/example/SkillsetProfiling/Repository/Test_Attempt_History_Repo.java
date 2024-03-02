package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Test_Attempt_History;
import com.example.SkillsetProfiling.Key.Test_Attempt_History_Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Test_Attempt_History_Repo extends JpaRepository<Test_Attempt_History, Test_Attempt_History_Key> {
}
