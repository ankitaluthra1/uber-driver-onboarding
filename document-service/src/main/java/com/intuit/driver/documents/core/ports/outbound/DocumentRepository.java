package com.intuit.driver.documents.core.ports.outbound;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import reactor.core.publisher.Mono;

public interface DocumentRepository {
     Mono<String> saveDocument(DocumentInfo documentInfo);
}
