package com.intuit.driver.documents.infrastructure;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import com.intuit.driver.documents.core.ports.outbound.DocumentCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DocumentRepository implements DocumentCollector {

    private VaultClient vaultClient;

    @Autowired
    public DocumentRepository(VaultClient vaultClient) {
        this.vaultClient = vaultClient;
    }

    @Override
    public Mono<String> saveDocument(DocumentInfo documentInfo) {
        return vaultClient.saveDocumentInVault(documentInfo.getFile());
    }
}
