package com.intuit.driver.profile.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import com.intuit.driver.profile.models.DriverProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import com.intuit.driver.profile.services.ProfileService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ProfileService profileService;

    @Test
    public void shouldReturnOkForGivenDriverProfile() {
        DriverProfile driverProfile = new DriverProfile("Ankita", "Luthra", "al@example.com", "987564321","some-uuid");
        Mono<String> responseMono = Mono.just("Profile created successfully");

        when(profileService.validateAndSaveProfile(any())).thenReturn(responseMono);

        webClient.post()
                .uri("/profiles/drivers/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(driverProfile))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Profile created successfully");
    }

    @Test
    public void shouldReturn500InternalServerIfProfileServiceReturnsException() {
        DriverProfile driverProfile = new DriverProfile("Ankita", "Luthra", "al@example.com", "987564321","some-uuid");
        Mono<String> responseMono = Mono.error(new NullPointerException("Profile cannot be successfully"));

        when(profileService.validateAndSaveProfile(any())).thenReturn(Mono.error(new NullPointerException("some-error")));

        webClient.post()
                .uri("/profiles/drivers/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(driverProfile), DriverProfile.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}