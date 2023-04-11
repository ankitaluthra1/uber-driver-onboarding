package com.intuit.driver.documents.core.ports.models;

import org.springframework.http.codec.multipart.FilePart;

public class DocumentInfo {

    private String phoneNumber;
    FilePart file;

    public DocumentInfo(String phoneNumber, FilePart file) {
        this.phoneNumber = phoneNumber;
        this.file = file;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public FilePart getFile() {
        return file;
    }
}
