package com.reportgeneration.controller;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @PostMapping("/save-personal-info")
    public ResponseEntity<String> savePersonalInfo(@RequestBody PersonalInfo personalInfo) {
        infoService.savePersonalInfo(personalInfo);
        return new ResponseEntity<>("Personal information saved successfully", HttpStatus.OK);
    }

    @PostMapping("/save-professional-info")
    public ResponseEntity<String> saveProfessionalInfo(@RequestBody ProfessionalInfo professionalInfo) {
        infoService.saveProfessionalInfo(professionalInfo);
        return new ResponseEntity<>("Professional information saved successfully", HttpStatus.OK);
    }
}