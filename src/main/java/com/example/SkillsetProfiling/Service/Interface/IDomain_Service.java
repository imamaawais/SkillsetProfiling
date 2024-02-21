package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Domain_DTO;

import java.util.List;

public interface IDomain_Service {
    Domain_DTO addDomain(Domain_DTO domainDTO);
    List<Domain_DTO> getAllDomains();
    Domain_DTO getDomainByDomainID(Integer domainID);
    Domain_DTO getDomainByDomainName(String domainName);
    Domain_DTO updateDomain(Integer domainID, Domain_DTO updatedDomainDTO);
    boolean deleteDomain(Integer domainID);


}
