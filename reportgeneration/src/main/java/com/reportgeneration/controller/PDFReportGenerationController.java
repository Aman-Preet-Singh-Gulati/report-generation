package com.reportgeneration.controller;

import com.reportgeneration.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class PDFReportGenerationController {

    private final ReportGenerationService reportGenerationService;

    @Autowired
    public PDFReportGenerationController(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @GetMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdfReport() {
        // Fetch all personal and professional info IDs from MongoDB (you may need custom logic)
        // For demonstration purposes, let's assume you have methods in InfoService to get all IDs
        // List<String> allPersonalInfoIds = infoService.getAllPersonalInfoIds();
        // List<String> allProfessionalInfoIds = infoService.getAllProfessionalInfoIds();

        // Call the service to generate the PDF report with all data
        byte[] pdfBytes = reportGenerationService.generatePdfReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdfBytes.length);
        headers.setContentDispositionFormData("inline", "report.pdf");
        headers.setContentDispositionFormData("attachment", "report.pdf");

        return new ResponseEntity<>(pdfBytes, headers, org.springframework.http.HttpStatus.OK);
    }
}