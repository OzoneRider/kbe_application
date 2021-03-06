package com.kbe.application.controller;

import com.kbe.application.services.csv.CSVExportService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CsvExportController {

    private final CSVExportService csvExportService;

    @GetMapping("products")
    public ResponseEntity<?> exportProductsToCSV(){
        try{
            csvExportService.exportCsvToFolder("products.csv");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(IOException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
