package com.intuit.driver.profile.models;

import com.intuit.driver.profile.exceptions.ValidationException;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverProfile {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String documentId;

    public DriverProfile() {
    }

    public DriverProfile(String firstName, String lastName, String email, String phoneNumber, String documentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.documentId = documentId;
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

    public void validate(){
        if(!StringUtils.hasText(documentId))
            throw new ValidationException("DocumentId is not present");
        if(!isValidEmail())
            throw new ValidationException("Email is invalid");
    }

    private boolean isValidEmail(){
        String PHONE_NUMBER_REGEX = "^(.+)@(.+)";
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
