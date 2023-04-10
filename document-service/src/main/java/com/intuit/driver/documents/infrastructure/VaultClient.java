package com.intuit.driver.documents.infrastructure;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;
import java.util.UUID;

@Component
public class VaultClient {

    public Mono<String> saveDocumentInVault(FilePart file){
        return file
                .transferTo(Paths
                        .get("/data/documents"+file.filename())).then(Mono.just(UUID.randomUUID().toString()));
    }

}
