package com.intuit.driver.documents.core.ports;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import com.intuit.driver.documents.core.ports.outbound.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DocumentProcessorTest {

    private DocumentRepository documentRepository;
    private DocumentProcessor documentProcessor;

    @BeforeEach
    void setUp() {
        documentRepository = mock(DocumentRepository.class);
        documentProcessor = new DocumentProcessor(documentRepository);
    }

    @Test
    void shouldSaveUploadedDocument() {
        DocumentInfo documentInfo = new DocumentInfo("aadhar", mock(FilePart.class));

        String expectedDocumentUUID = "UUID-123";
        when(documentRepository.saveDocument(documentInfo)).thenReturn(Mono.just(expectedDocumentUUID));

        Mono<String> actualUUID = documentProcessor.saveDocument(documentInfo);

        verify(documentRepository).saveDocument(documentInfo);
        assertEquals(expectedDocumentUUID, actualUUID.block());
    }
}