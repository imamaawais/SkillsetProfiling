package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Industry_DTO;

import java.util.List;

public interface IIndustry_Service {

    Industry_DTO addIndustry(Industry_DTO industryDTO);
    List<Industry_DTO> getAllIndustries();
    Industry_DTO getIndustryByID(Integer industryID);
    Industry_DTO getIndustryByName(String industryName) ;
    Industry_DTO updateIndustry(Integer industryID, Industry_DTO updatedIndustryDTO) ;
    boolean deleteIndustry(Integer industryID);

}
