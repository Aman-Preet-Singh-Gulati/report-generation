package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

@Service
public class RtfFileGenerationServiceImpl implements RtfFileGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public RtfFileGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                        ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public byte[] generateRtfFileReport() {
        // Fetch all personal and professional information from MongoDB
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();

        // Create RTF file content
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Writer writer = new OutputStreamWriter(baos)) {

            // RTF header
            writer.write("{\\rtf1\\ansi\\deff0\n");

            // Add personal information to the RTF file
            writer.write("\\par Personal Information:\\par ");
            for (PersonalInfo personalInfo : allPersonalInfo) {
                writer.write("Name: " + personalInfo.getFirstName() + " " + personalInfo.getLastName() +
                        ", Occupation: " + personalInfo.getOccupation() + "\\par ");
            }

            // Add an empty line as a separator
            writer.write("\\par\n");

            // Add professional information to the RTF file
            writer.write("Professional Information:\\par ");
            for (ProfessionalInfo professionalInfo : allProfessionalInfo) {
                writer.write("Total Experience: " + professionalInfo.getTotalExperience() +
                        " years, Job Status: " + professionalInfo.getJobStatus() + "\\par ");
            }

            // RTF footer
            writer.write("}\n");

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
