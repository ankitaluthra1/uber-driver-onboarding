package com.intuit.driver.documents.application;

import com.intuit.driver.documents.core.ports.inbound.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;


@WebFluxTest(DocumentController.class)
class DocumentControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    DocumentService documentService;


}