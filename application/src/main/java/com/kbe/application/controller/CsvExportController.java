package com.kbe.application.controller;

import com.kbe.application.services.csv.CSVExportService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/export/")
@CommonsLog
public class CsvExportController {

    private final CSVExportService csvExportService;

    public CsvExportController(CSVExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @GetMapping("products")
    public ResponseEntity<?> exportProductsToCSV(){
        try{
            csvExportService.exportCsvToFolder();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(IOException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
