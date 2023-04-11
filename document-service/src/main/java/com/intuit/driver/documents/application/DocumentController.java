package com.intuit.driver.documents.application;


import com.intuit.driver.documents.core.ports.inbound.DocumentService;
import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<String> uploadFile(@RequestParam String phoneNumber, @RequestPart Mono<FilePart> file) {
        return file.flatMap(f -> documentService.saveDocument(new DocumentInfo(phoneNumber, f)));
    }
}
