package com.reportgeneration.controller;

import com.reportgeneration.service.CsvGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv")
public class CsvGenerationController {

    private final CsvGenerationService csvGenerationService;

    @Autowired
    public CsvGenerationController(CsvGenerationService csvGenerationService) {
        this.csvGenerationService = csvGenerationService;
    }

    @GetMapping(value = "/generate", produces = "text/csv")
    public ResponseEntity<byte[]> generateCsvReport() {
        byte[] csvBytes = csvGenerationService.generateCsvReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentLength(csvBytes.length);
        headers.setContentDispositionFormData("inline", "report.csv");
        headers.setContentDispositionFormData("attachment", "report.csv");

        return new ResponseEntity<>(csvBytes, headers, org.springframework.http.HttpStatus.OK);
    }
}
