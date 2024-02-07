package com.reportgeneration.repository;

import com.reportgeneration.model.PersonalInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String> {

}
