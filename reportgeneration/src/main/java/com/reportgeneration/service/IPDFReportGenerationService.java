package com.reportgeneration.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;

public interface IPDFReportGenerationService {
    ByteArrayInputStream generateIPdfReport() throws DocumentException;
}
