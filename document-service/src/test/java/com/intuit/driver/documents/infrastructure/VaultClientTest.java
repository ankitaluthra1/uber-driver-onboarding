package com.intuit.driver.documents.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VaultClientTest {

    private VaultClient vaultClient;

    @BeforeEach
    void setUp() {
        vaultClient = new VaultClient();
    }

    @Test
    public void saveDocumentInVault() {
        FilePart filePart = mock(FilePart.class);
        String filename = "test-file";
        when(filePart.filename()).thenReturn(filename);
        when(filePart.transferTo(any(Path.class))).thenReturn(Mono.empty());

        String result = vaultClient.saveDocumentInVault(filePart).block();

        assertNotNull(result);
        assertTrue(result.matches("^[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}$"));
        verify(filePart).transferTo(Paths.get("/data/documents"+filename));
    }
}