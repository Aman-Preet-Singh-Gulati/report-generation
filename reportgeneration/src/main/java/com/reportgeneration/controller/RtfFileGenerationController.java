package com.reportgeneration.controller;

import com.reportgeneration.service.RtfFileGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rtf")
public class RtfFileGenerationController {

    private final RtfFileGenerationService rtfFileGenerationService;

    @Autowired
    public RtfFileGenerationController(RtfFileGenerationService rtfFileGenerationService) {
        this.rtfFileGenerationService = rtfFileGenerationService;
    }

    @GetMapping(value = "/generate", produces = "application/rtf")
    public ResponseEntity<byte[]> generateRtfFileReport() {
        byte[] rtfFileBytes = rtfFileGenerationService.generateRtfFileReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/rtf"));
        headers.setContentLength(rtfFileBytes.length);
        headers.setContentDispositionFormData("attachment", "report.rtf"); // Set the filename with .rtf extension

        return new ResponseEntity<>(rtfFileBytes, headers, org.springframework.http.HttpStatus.OK);
    }
}
