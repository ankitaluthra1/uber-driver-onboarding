package com.intuit.driver.documents.infrastructure;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import com.intuit.driver.documents.core.ports.outbound.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DocumentRepositoryImpl implements DocumentRepository {

    private VaultClient vaultClient;

    @Autowired
    public DocumentRepositoryImpl(VaultClient vaultClient) {
        this.vaultClient = vaultClient;
    }

    @Override
    public Mono<String> saveDocument(DocumentInfo documentInfo) {
        return vaultClient.saveDocumentInVault(documentInfo.getFile());
    }
}
