package com.reportgeneration.service;

import com.itextpdf.text.pdf.PdfPCell;
import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class IPDFReportGenerationServiceImpl implements IPDFReportGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public IPDFReportGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                           ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public ByteArrayInputStream generateIPdfReport() throws DocumentException {
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();
        return exportToPdf(allPersonalInfo, allProfessionalInfo);
    }

    private ByteArrayInputStream exportToPdf(List<PersonalInfo> personalInfoList, List<ProfessionalInfo> professionalInfoList) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        PdfPTable personalTable = createPersonalInfoTable(personalInfoList);
        PdfPTable professionalTable = createProfessionalInfoTable(professionalInfoList);

        // Add space between tables
        document.add(personalTable);
        document.add(new Paragraph("\n\n"));
        document.add(professionalTable);

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private PdfPTable createPersonalInfoTable(List<PersonalInfo> personalInfoList) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        // Set table header font
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);

        // Add table headers
        table.addCell(getCell("First Name", headerFont));
        table.addCell(getCell("Last Name", headerFont));
        table.addCell(getCell("Occupation", headerFont));

        // Add data rows
        for (PersonalInfo personalInfo : personalInfoList) {
            table.addCell(personalInfo.getFirstName());
            table.addCell(personalInfo.getLastName());
            table.addCell(personalInfo.getOccupation());
        }

        return table;
    }

    private PdfPTable createProfessionalInfoTable(List<ProfessionalInfo> professionalInfoList) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Set table header font
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);

        // Add table headers
        table.addCell(getCell("Total Experience", headerFont));
        table.addCell(getCell("Job Status", headerFont));

        // Add data rows
        for (ProfessionalInfo professionalInfo : professionalInfoList) {
            table.addCell(String.valueOf(professionalInfo.getTotalExperience()));
            table.addCell(professionalInfo.getJobStatus());
        }

        return table;
    }

    // Helper method to create cell with specified font
    private PdfPCell getCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
