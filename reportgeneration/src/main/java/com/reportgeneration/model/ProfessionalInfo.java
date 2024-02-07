package com.reportgeneration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "professional_info")
public class ProfessionalInfo {

    @Id
    private String id;
    private int totalExperience;
    private String jobStatus;

    public ProfessionalInfo() {
    }

    public ProfessionalInfo(String id, int totalExperience, String jobStatus) {
        this.id = id;
        this.totalExperience = totalExperience;
        this.jobStatus = jobStatus;
    }

    public ProfessionalInfo(int totalExperience, String jobStatus) {
        this.totalExperience = totalExperience;
        this.jobStatus = jobStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public String toString() {
        return "ProfessionalInfo{" +
                "id='" + id + '\'' +
                ", totalExperience=" + totalExperience +
                ", jobStatus='" + jobStatus + '\'' +
                '}';
    }
}
