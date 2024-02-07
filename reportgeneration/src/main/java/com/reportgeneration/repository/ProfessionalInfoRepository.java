package com.reportgeneration.repository;

import com.reportgeneration.model.ProfessionalInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessionalInfoRepository extends MongoRepository<ProfessionalInfo, String> {

}
