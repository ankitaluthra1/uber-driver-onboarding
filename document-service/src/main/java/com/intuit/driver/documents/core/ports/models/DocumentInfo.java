package com.intuit.driver.documents.core.ports.models;

import org.springframework.http.codec.multipart.FilePart;

public class DocumentInfo {

    private String type;
    FilePart file;

    public DocumentInfo(String type, FilePart file) {
        this.type = type;
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public FilePart getFile() {
        return file;
    }
}
