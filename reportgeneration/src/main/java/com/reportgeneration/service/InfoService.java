package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;

public interface InfoService {

    void savePersonalInfo(PersonalInfo personalInfo);

    void saveProfessionalInfo(ProfessionalInfo professionalInfo);

    PersonalInfo getPersonalInfoById(String id);

    ProfessionalInfo getProfessionalInfoById(String id);
}