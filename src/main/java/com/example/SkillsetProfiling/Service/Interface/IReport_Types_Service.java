package com.example.SkillsetProfiling.Service.Interface;

import com.example.SkillsetProfiling.Dto.Report_Types_DTO;
import com.example.SkillsetProfiling.Exception.ReportTypesNotFoundException;

import java.util.List;

public interface IReport_Types_Service {

    Report_Types_DTO addReportType(Report_Types_DTO reportTypesDTO);
    Report_Types_DTO getReportTypeById(Integer ReportTypesID) throws ReportTypesNotFoundException;
    List<Report_Types_DTO> getAllReportTypes();
    Report_Types_DTO updateReportType(Integer ReportTypesID, Report_Types_DTO updatedReportTypesDTO);

    boolean deleteReportType(Integer ReportTypesID);
}
