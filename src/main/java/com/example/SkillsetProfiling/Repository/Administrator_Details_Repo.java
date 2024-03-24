package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Administrator_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Administrator_Details_Repo extends JpaRepository<Administrator_Details, Integer> {
    @Query("SELECT a.AdministratorID FROM Administrator_Details a WHERE a.userDetails.auth.email = :email")
    Optional<Integer> findByUserDetails_Auth_Email(String email);
}
