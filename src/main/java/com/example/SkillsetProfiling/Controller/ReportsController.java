package com.example.SkillsetProfiling.Controller;

import com.example.SkillsetProfiling.Dto.Reports_DTO;
import com.example.SkillsetProfiling.Exception.ReportsNotFoundException;
import com.example.SkillsetProfiling.Service.Implementation.Reports_Service;
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

    private final Reports_Service service;

    @PostMapping("/add")
    public ResponseEntity<Reports_DTO> addReport(@RequestBody Reports_DTO reportsDTO) {
        try {
            Reports_DTO savedReport = service.addReport(reportsDTO);
            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Reports_DTO>> getAllReports() {
        List<Reports_DTO> reports = service.getAllReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/getAllPending")
    public ResponseEntity<List<Reports_DTO>> getAllPendingReports() {
        List<Reports_DTO> reports = service.getAllPendingReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/getAllNotPending")
    public ResponseEntity<List<Reports_DTO>> getAllNotPendingReports() {
        List<Reports_DTO> reports = service.getAllNotPendingReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/getFromId/{ReportID}")
    public ResponseEntity<Reports_DTO> getReportById(@PathVariable int ReportID) {
        try {
            Reports_DTO reportsDTO = service.getReportById(ReportID);
            return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
        } catch (ReportsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{ReportID}")
    public ResponseEntity<Reports_DTO> updateReport(@PathVariable Integer ReportID, @RequestBody Reports_DTO updatedReportDTO) {
        try {
            Reports_DTO updatedReport = service.updateReport(ReportID, updatedReportDTO);
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } catch (ReportsNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{ReportID}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer ReportID) {
        try {
            boolean deleted = service.deleteReport(ReportID);
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
