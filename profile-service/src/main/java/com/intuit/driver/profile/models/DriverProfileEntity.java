package com.intuit.driver.profile.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "driver-profiles")
public class DriverProfileEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private String documentId;

    private DriverProfileEntity(String firstName, String lastName, String email, String phoneNumber, String documentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.documentId = documentId;
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

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDocumentId() {
        return documentId;
    }

    public static DriverProfileEntity from(DriverProfile driverProfile){
        return new DriverProfileEntity(
                driverProfile.getFirstName(),
                driverProfile.getLastName(),
                driverProfile.getEmail(),
                driverProfile.getPhoneNumber(),
                driverProfile.getDocumentId()
        );
    }
}
