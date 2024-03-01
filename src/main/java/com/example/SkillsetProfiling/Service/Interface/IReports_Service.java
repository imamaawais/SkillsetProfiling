package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Reports_DTO;
import com.example.SkillsetProfiling.Exception.ReportsNotFoundException;

import java.util.List;

public interface IReports_Service {

    Reports_DTO addReport(Reports_DTO reportsDTO);

    Reports_DTO getReportById(Integer reportId) throws ReportsNotFoundException;

    List<Reports_DTO> getAllReports();

    Reports_DTO updateReport(Integer reportId, Reports_DTO updatedReportsDTO);

    boolean deleteReport(Integer reportId);
}
