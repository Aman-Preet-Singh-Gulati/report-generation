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
public class CsvGenerationServiceImpl implements CsvGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public CsvGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                    ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public byte[] generateCsvReport() {
        // Fetch all personal and professional information from MongoDB
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();

        // Create CSV content
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(baos)) {

            // Add CSV headers
            writer.println("First Name,Last Name,Occupation");

            // Add personal information to the CSV
            for (PersonalInfo personalInfo : allPersonalInfo) {
                writer.println(personalInfo.getFirstName() + "," + personalInfo.getLastName() + ","
                        + personalInfo.getOccupation());
            }

            // Add an empty line as a separator
            writer.println();

            // Add CSV headers for professional information
            writer.println("Total Experience,Job Status");

            // Add professional information to the CSV
            for (ProfessionalInfo professionalInfo : allProfessionalInfo) {
                writer.println(professionalInfo.getTotalExperience() + "," + professionalInfo.getJobStatus());
            }
            writer.flush();
            
            return baos.toByteArray();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }
}
