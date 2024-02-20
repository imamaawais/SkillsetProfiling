package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Auth_Repo extends JpaRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);

}
