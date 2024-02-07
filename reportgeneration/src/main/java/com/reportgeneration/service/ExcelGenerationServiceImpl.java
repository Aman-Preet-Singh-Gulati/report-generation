package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGenerationServiceImpl implements ExcelGenerationService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public ExcelGenerationServiceImpl(PersonalInfoRepository personalInfoRepository,
                                      ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public byte[] generateExcelReport() {
        // Fetch all personal and professional information from MongoDB
        List<PersonalInfo> allPersonalInfo = personalInfoRepository.findAll();
        List<ProfessionalInfo> allProfessionalInfo = professionalInfoRepository.findAll();

        // Create Excel workbook and sheet
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Workbook workbook = new HSSFWorkbook()) { // Use HSSFWorkbook for Excel 97-2003 format (.xls)

            Sheet sheet = workbook.createSheet("Report");

            // Create headers
            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("First Name");
            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Last Name");
            Cell headerCell3 = headerRow.createCell(2);
            headerCell3.setCellValue("Occupation");

            // Add personal information to the sheet
            int rowNum = 1;
            for (PersonalInfo personalInfo : allPersonalInfo) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(personalInfo.getFirstName());
                row.createCell(1).setCellValue(personalInfo.getLastName());
                row.createCell(2).setCellValue(personalInfo.getOccupation());
            }

            // Add an empty row as a separator
            rowNum++;

            // Create headers for professional information
            Row headerRowProfessional = sheet.createRow(rowNum++);
            Cell headerCell4 = headerRowProfessional.createCell(0);
            headerCell4.setCellValue("Total Experience");
            Cell headerCell5 = headerRowProfessional.createCell(1);
            headerCell5.setCellValue("Job Status");

            // Add professional information to the sheet
            for (ProfessionalInfo professionalInfo : allProfessionalInfo) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(professionalInfo.getTotalExperience());
                row.createCell(1).setCellValue(professionalInfo.getJobStatus());
            }

            // Auto-size columns
            for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the workbook content to the output stream
            workbook.write(baos);

            return baos.toByteArray();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }
}
