package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class TextFileGenerationServiceImpl implements TextFileGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public TextFileGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                         ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public byte[] generateTextFileReport() {
        // Fetch all personal and professional information from MongoDB
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();

        // Create text file content
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(baos)) {

            // Add personal information to the text file
            writer.println("Personal Information:");
            for (PersonalInfo personalInfo : allPersonalInfo) {
                writer.println("Name: " + personalInfo.getFirstName() + " " + personalInfo.getLastName() +
                        ", Occupation: " + personalInfo.getOccupation());
            }

            // Add an empty line as a separator
            writer.println();

            // Add professional information to the text file
            writer.println("Professional Information:");
            for (ProfessionalInfo professionalInfo : allProfessionalInfo) {
                writer.println("Total Experience: " + professionalInfo.getTotalExperience() +
                        " years, Job Status: " + professionalInfo.getJobStatus());
            }

            // Ensure the writer is flushed
            writer.flush();

            return baos.toByteArray();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }
}
