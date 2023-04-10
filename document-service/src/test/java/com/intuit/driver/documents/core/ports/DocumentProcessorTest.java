package com.intuit.driver.documents.core.ports;

import com.intuit.driver.documents.core.ports.models.DocumentInfo;
import com.intuit.driver.documents.core.ports.outbound.DocumentCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.mock.web.MockMultipartFile;
import reactor.core.publisher.Mono;

import javax.swing.text.Document;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DocumentProcessorTest {

    private DocumentCollector documentCollector;
    private DocumentProcessor documentProcessor;

    @BeforeEach
    void setUp() {
        documentCollector = mock(DocumentCollector.class);
        documentProcessor = new DocumentProcessor(documentCollector);
    }

    @Test
    void shouldSaveUploadedDocument() {
        DocumentInfo documentInfo = new DocumentInfo("aadhar", mock(FilePart.class));

        String expectedDocumentUUID = "UUID-123";
        when(documentCollector.saveDocument(documentInfo)).thenReturn(Mono.just(expectedDocumentUUID));

        Mono<String> actualUUID = documentProcessor.saveDocument(documentInfo);

        verify(documentCollector).saveDocument(documentInfo);
        assertEquals(expectedDocumentUUID, actualUUID.block());
    }
}