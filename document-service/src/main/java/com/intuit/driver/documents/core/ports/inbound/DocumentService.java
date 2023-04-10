package com.intuit.driver.documents.core.ports.inbound;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import reactor.core.publisher.Mono;

public interface DocumentService {
    Mono<String> saveDocument(DocumentInfo documentInfo);
}
