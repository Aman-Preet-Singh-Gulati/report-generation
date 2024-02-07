package com.reportgeneration.controller;

import com.reportgeneration.service.ExcelGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/excel")
public class ExcelGenerationController {

    private final ExcelGenerationService excelGenerationService;

    @Autowired
    public ExcelGenerationController(ExcelGenerationService excelGenerationService) {
        this.excelGenerationService = excelGenerationService;
    }

    @GetMapping(value = "/generate", produces = "application/vnd.ms-excel")
    public ResponseEntity<byte[]> generateExcelReport() {
        byte[] excelBytes = excelGenerationService.generateExcelReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        headers.setContentLength(excelBytes.length);
        headers.setContentDispositionFormData("attachment", "report.xls"); // Set the filename with .xls extension

        return new ResponseEntity<>(excelBytes, headers, org.springframework.http.HttpStatus.OK);
    }
}
