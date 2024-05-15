package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Reports_DTO;
import com.example.SkillsetProfiling.Entity.Reports;
import com.example.SkillsetProfiling.Exception.DuplicateReportException;
import com.example.SkillsetProfiling.Exception.ReportsNotFoundException;
import com.example.SkillsetProfiling.Repository.Reports_Repo;
import com.example.SkillsetProfiling.Service.Interface.IReports_Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Reports_Service implements IReports_Service {

    private final Reports_Repo reportsRepo;
    private final ModelMapper mapper;

    @Override
    public Reports_DTO addReport(Reports_DTO reportsDTO) {
        Reports report = mapper.map(reportsDTO, Reports.class);

        Reports savedReport = reportsRepo.save(report);
        return mapper.map(savedReport, Reports_DTO.class);
    }


    @Override
    public Reports_DTO getReportById(Integer reportId) throws ReportsNotFoundException {
        Optional<Reports> reportOptional = reportsRepo.findById(reportId);
        if (reportOptional.isPresent()) {
            return mapper.map(reportOptional.get(), Reports_DTO.class);
        } else {
            throw new ReportsNotFoundException("Report not found with ID: " + reportId);
        }
    }

    @Override
    public List<Reports_DTO> getAllReports() {
        List<Reports> reports = reportsRepo.findAll();
        return reports.stream()
                .map(report -> mapper.map(report, Reports_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Reports_DTO> getAllPendingReports() {
        List<Reports> reports = reportsRepo.getAllPendingCases();
        return reports.stream()
                .map(report -> mapper.map(report, Reports_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Reports_DTO> getAllNotPendingReports() {
        List<Reports> reports = reportsRepo.getAllNotPendingCases();
        return reports.stream()
                .map(report -> mapper.map(report, Reports_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Reports_DTO updateReport(Integer reportId, Reports_DTO updatedReportsDTO) throws ReportsNotFoundException {
        Optional<Reports> reportOptional = reportsRepo.findById(reportId);
        if (reportOptional.isPresent()) {
            Reports existingReport = reportOptional.get();

            existingReport.setReportDescription(updatedReportsDTO.getReportDescription());
            existingReport.setReportStatus(updatedReportsDTO.getReportStatus());
            existingReport.setReportTypes(updatedReportsDTO.getReportTypes());
            existingReport.setUserDetails(updatedReportsDTO.getUserDetails());

            // Set other fields as needed
            Reports updatedReport = reportsRepo.save(existingReport);
            return mapper.map(updatedReport, Reports_DTO.class);
        } else {
            throw new ReportsNotFoundException("Report not found with ID: " + reportId);
        }
    }

    @Override
    public boolean deleteReport(Integer reportId) throws ReportsNotFoundException {
        Optional<Reports> reportOptional = reportsRepo.findById(reportId);
        if (reportOptional.isPresent()) {
            reportsRepo.delete(reportOptional.get());
            return true;
        } else {
            throw new ReportsNotFoundException("Report not found with ID: " + reportId);
        }
    }
}
