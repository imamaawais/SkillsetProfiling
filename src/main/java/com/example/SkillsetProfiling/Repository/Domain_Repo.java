package com.example.SkillsetProfiling.Repository;

import com.example.SkillsetProfiling.Entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Domain_Repo extends JpaRepository<Domain, Integer> {
    Optional<Domain> findByDomainName(String domainName);
}
