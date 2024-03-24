package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Placement_Coordinator_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Placement_Coordinator_Details_Repo extends JpaRepository<Placement_Coordinator_Details, Integer> {
    @Query("SELECT p.CoordinatorID FROM Placement_Coordinator_Details p WHERE p.userDetails.auth.email = :email")
    Optional<Integer> findByUserDetails_Auth_Email(String email);
}
