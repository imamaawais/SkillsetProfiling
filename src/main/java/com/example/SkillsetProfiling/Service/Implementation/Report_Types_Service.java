package com.example.SkillsetProfiling.Service.Implementation;

import com.example.SkillsetProfiling.Dto.Report_Types_DTO;
import com.example.SkillsetProfiling.Entity.Report_Types;
import com.example.SkillsetProfiling.Exception.DuplicateReportTypeException;
import com.example.SkillsetProfiling.Exception.ReportTypesNotFoundException;
import com.example.SkillsetProfiling.Repository.Report_Types_Repo;
import com.example.SkillsetProfiling.Service.Interface.IReport_Types_Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Report_Types_Service implements IReport_Types_Service {

    private ModelMapper mapper;
    private Report_Types_Repo reportTypesRepo;
    @Override
    public Report_Types_DTO addReportType(Report_Types_DTO reportTypesDTO) {

        Report_Types reportTypes = mapper.map(reportTypesDTO, Report_Types.class);



        //save to db
        Report_Types savedReportType = reportTypesRepo.save(reportTypes);
        Report_Types_DTO savedReportTypeDTO = mapper.map(savedReportType, Report_Types_DTO.class);

        return savedReportTypeDTO;
    }

    @Override
    public Report_Types_DTO getReportTypeById(Integer ReportTypesID) throws ReportTypesNotFoundException {
        Optional<Report_Types> reportTypesOptional = reportTypesRepo.findById(ReportTypesID);

        if (reportTypesOptional.isPresent()) {
            return mapper.map(reportTypesOptional.get(), Report_Types_DTO.class);
        } else {
            throw new ReportTypesNotFoundException("Report Type not found with id: " + ReportTypesID);
        }
    }

    @Override
    public List<Report_Types_DTO> getAllReportTypes() {
        List<Report_Types> reportTypes = reportTypesRepo.findAll();

        return reportTypes.stream()
                .map(reportType-> mapper.map(reportType, Report_Types_DTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Report_Types_DTO updateReportType(Integer ReportTypesID, Report_Types_DTO updatedReportTypesDTO) {
        Optional<Report_Types> reportTypesOptional = reportTypesRepo.findById(ReportTypesID);

        if (reportTypesOptional.isPresent()) {
            Report_Types existingReportType = reportTypesOptional.get();

            existingReportType.setTypeName(updatedReportTypesDTO.getTypeName());

            Report_Types updatedReportType = reportTypesRepo.save(existingReportType);

            return mapper.map(updatedReportType, Report_Types_DTO.class);
        } else {
            throw new ReportTypesNotFoundException("Report Type not found with ID: " + ReportTypesID);
        }
    }

    @Override
    public boolean deleteReportType(Integer ReportTypesID) {
            Optional<Report_Types> reportTypesOptional = reportTypesRepo.findById(ReportTypesID);

        if (reportTypesOptional.isPresent()) {
            Report_Types reportTypesToDelete = reportTypesOptional.get();
            reportTypesRepo.delete(reportTypesToDelete);
            return true; // Deletion successful
        } else {
            throw new ReportTypesNotFoundException("Report Type not found with ID: " + ReportTypesID);
        }
    }
}
