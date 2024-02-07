package com.reportgeneration.controller;

import com.reportgeneration.service.IPDFReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/report")
public class IPDFReportGenerationController {

    private final IPDFReportGenerationService ipdfReportGenerationService;

    @Autowired
    public IPDFReportGenerationController(IPDFReportGenerationService ipdfReportGenerationService) {
        this.ipdfReportGenerationService = ipdfReportGenerationService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> generateIPdfReport() {
        try {
            ByteArrayInputStream pdfInputStream = ipdfReportGenerationService.generateIPdfReport();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=report.pdf");
            ResponseEntity.BodyBuilder ok = ResponseEntity
                    .ok();
            ok.headers(headers);
            ok.contentType(MediaType.APPLICATION_PDF);
            return ok
                    .body(new InputStreamResource(pdfInputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}

