package com.reportgeneration.service;

import com.reportgeneration.model.PersonalInfo;
import com.reportgeneration.model.ProfessionalInfo;
import com.reportgeneration.repository.PersonalInfoRepository;
import com.reportgeneration.repository.ProfessionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProfessionalInfoRepository professionalInfoRepository;

    @Autowired
    public InfoServiceImpl(PersonalInfoRepository personalInfoRepository,
                           ProfessionalInfoRepository professionalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.professionalInfoRepository = professionalInfoRepository;
    }

    @Override
    public void savePersonalInfo(PersonalInfo personalInfo) {
        personalInfoRepository.save(personalInfo);
    }

    @Override
    public void saveProfessionalInfo(ProfessionalInfo professionalInfo) {
        professionalInfoRepository.save(professionalInfo);
    }

    @Override
    public PersonalInfo getPersonalInfoById(String id) {
        return personalInfoRepository.findById(id).orElse(null);
    }

    @Override
    public ProfessionalInfo getProfessionalInfoById(String id) {
        return professionalInfoRepository.findById(id).orElse(null);
    }
}