package com.intuit.driver.documents.core.ports;

import com.intuit.driver.documents.core.ports.inbound.DocumentService;
import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import com.intuit.driver.documents.core.ports.outbound.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DocumentProcessor implements DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentProcessor(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Mono<String> saveDocument(DocumentInfo documentInfo) {
        return documentRepository.saveDocument(documentInfo);
    }
}
