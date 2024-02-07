package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public ReportGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                       ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public byte[] generatePdfReport() {
        // Fetch all personal and professional information from MongoDB
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();

        // Create PDF content using Apache PDFBox
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.newLineAtOffset(100, 700);

                // Add content to the PDF, e.g., iterate through PersonalInfo and ProfessionalInfo
                contentStream.showText("PDF Report ");
                System.out.println("PDF Report\n");

                // Add personal information to the PDF
                contentStream.showText("Personal Information:");
                System.out.println("Personal Information\n");
                for (PersonalInfo personalInfo : allPersonalInfo) {
                    contentStream.showText("Name: " + personalInfo.getFirstName() + " " + personalInfo.getLastName() +
                            ", Occupation: " + personalInfo.getOccupation() + " ");
                    System.out.println("Name: " + personalInfo.getFirstName() + " " + personalInfo.getLastName() +
                            ", Occupation: " + personalInfo.getOccupation() + "\n");
                }

                // Add professional information to the PDF
                contentStream.showText("Professional Information:");
                System.out.println("Professional Information:\n");

                for (ProfessionalInfo professionalInfo : allProfessionalInfo) {
                    contentStream.showText("Total Experience: " + professionalInfo.getTotalExperience() +
                            " years, Job Status: " + professionalInfo.getJobStatus() + " ");
                    System.out.println("Total Experience: " + professionalInfo.getTotalExperience() +
                            " years, Job Status: " + professionalInfo.getJobStatus() + "\n");
                }

                contentStream.endText();
            }

            document.save(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }
}