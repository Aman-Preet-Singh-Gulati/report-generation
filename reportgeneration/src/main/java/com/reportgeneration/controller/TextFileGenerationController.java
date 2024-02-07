package com.reportgeneration.controller;

import com.reportgeneration.service.TextFileGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text")
public class TextFileGenerationController {

    private final TextFileGenerationService textFileGenerationService;

    @Autowired
    public TextFileGenerationController(TextFileGenerationService textFileGenerationService) {
        this.textFileGenerationService = textFileGenerationService;
    }

    @GetMapping(value = "/generate", produces = "text/plain")
    public ResponseEntity<byte[]> generateTextFileReport() {
        byte[] textFileBytes = textFileGenerationService.generateTextFileReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/plain"));
        headers.setContentLength(textFileBytes.length);
        headers.setContentDispositionFormData("inline", "report.txt");
        headers.setContentDispositionFormData("attachment", "report.txt"); // Set the filename with .txt extension

        return new ResponseEntity<>(textFileBytes, headers, org.springframework.http.HttpStatus.OK);
    }
}
