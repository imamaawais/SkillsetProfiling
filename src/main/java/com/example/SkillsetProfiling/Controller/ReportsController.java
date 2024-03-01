package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Report_Types_DTO;
import com.example.SkillsetProfiling.Exception.ReportsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Report_Types_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/reports")
public class ReportsController {

    private final Report_Types_Service service;

    @PostMapping("/add")
    public ResponseEntity<Report_Types_DTO> addReportType(@RequestBody Report_Types_DTO reportTypesDTO) {
        try {
            Report_Types_DTO savedReportType = service.addReportType(reportTypesDTO);
            return new ResponseEntity<>(savedReportType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Report_Types_DTO>> getAllReportTypes() {
        List<Report_Types_DTO> reportTypes = service.getAllReportTypes();
        if (reportTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reportTypes, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{ReportTypeID}")
    public ResponseEntity<Report_Types_DTO> getReportTypeById(@PathVariable int ReportTypeID) {
        try {
            Report_Types_DTO reportTypesDTO = service.getReportTypeById(ReportTypeID);
            return new ResponseEntity<>(reportTypesDTO, HttpStatus.OK);
        } catch (ReportsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{ReportTypeID}")
    public ResponseEntity<Report_Types_DTO> updateReportType(@PathVariable Integer ReportTypeID, @RequestBody Report_Types_DTO updatedReportTypesDTO) {
        try {
            Report_Types_DTO updatedReportType = service.updateReportType(ReportTypeID, updatedReportTypesDTO);
            return new ResponseEntity<>(updatedReportType, HttpStatus.OK);
        } catch (ReportsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{ReportTypeID}")
    public ResponseEntity<Void> deleteReportType(@PathVariable Integer ReportTypeID) {
        try {
            boolean deleted = service.deleteReportType(ReportTypeID);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ReportsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
