package com.intuit.driver.documents.application;

import com.intuit.driver.documents.core.ports.inbound.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebFluxTest(DocumentController.class)
class DocumentControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    DocumentService documentService;

    @Test
    public void shouldReturnHttpStatusCreatedOnDocumentUpload() throws FileNotFoundException {
        Mono<String> responseMono = Mono.just("UUID-123");

        when(documentService.saveDocument(any())).thenReturn(responseMono);

        File file = ResourceUtils.getFile("classpath:test-file-upload.txt");
        FileSystemResource resource = new FileSystemResource(file.getAbsolutePath());
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", resource);

        webClient.post()
                .uri("/documents/upload?phoneNumber=555-555-5555")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class).isEqualTo("UUID-123");
    }

    @Test
    public void shouldReturnInternalServerErrorWhenFileUploadFails() throws FileNotFoundException {
        when(documentService.saveDocument(any())).thenThrow(new RuntimeException("Error while upload..."));

        File file = ResourceUtils.getFile("classpath:test-file-upload.txt");
        FileSystemResource resource = new FileSystemResource(file.getAbsolutePath());
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", resource);

        webClient.post()
                .uri("/documents/upload?phoneNumber=555-555-5555")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().is5xxServerError();
    }
}