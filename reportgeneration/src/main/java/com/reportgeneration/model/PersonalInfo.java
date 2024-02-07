package com.reportgeneration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personal_info")
public class PersonalInfo {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String occupation;

    public PersonalInfo() {
    }

    public PersonalInfo(String id, String firstName, String lastName, String occupation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
    }

    public PersonalInfo(String firstName, String lastName, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}