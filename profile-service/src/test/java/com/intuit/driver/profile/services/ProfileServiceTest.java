package com.intuit.driver.profile.services;

import static org.junit.jupiter.api.Assertions.*;

import com.intuit.driver.profile.exceptions.ValidationException;
import com.intuit.driver.profile.models.DriverProfile;
import com.intuit.driver.profile.models.DriverProfileEntity;
import com.intuit.driver.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    private ProfileRepository profileRepository;

    @BeforeEach
    public void setup(){
        profileRepository = mock(ProfileRepository.class);
    }

    @Test
    public void shouldPassDerivedEntityFromGivenDriveProfile() {
        DriverProfile driverProfile = new DriverProfile("some", "name", "some@example.com", "555-555-5555", "1234");
        DriverProfileEntity expectedEntity = DriverProfileEntity.from(driverProfile);
        expectedEntity.setId("id");

        when(profileRepository.save(any())).thenReturn(Mono.just(expectedEntity));

        ProfileService profileService = new ProfileService(profileRepository);
        profileService.validateAndSaveProfile(driverProfile);

        ArgumentCaptor<DriverProfileEntity> captor = ArgumentCaptor.forClass(DriverProfileEntity.class);
        verify(profileRepository, times(1)).save(captor.capture());
        assertEquals(expectedEntity.getId(), "id");
        assertEquals(expectedEntity.getDocumentId(), "1234");
    }

    @Test
    public void shouldReturnIdFromGivenEntity() {
        DriverProfile driverProfile = new DriverProfile("some", "name", "some@example.com", "555-555-5555", "1234");
        DriverProfileEntity expectedEntity = DriverProfileEntity.from(driverProfile);
        expectedEntity.setId("id");

        when(profileRepository.save(any())).thenReturn(Mono.just(expectedEntity));

        ProfileService profileService = new ProfileService(profileRepository);
        Mono<String> result = profileService.validateAndSaveProfile(driverProfile);

        assertNotNull(result);
        assertEquals("id", result.block());
    }

    @Test
    public void shouldThrowValidationExceptionForInvalidEmail() {
        DriverProfile driverProfile = new DriverProfile("some", "name", "invalid", "555", "1234");

        ProfileService profileService = new ProfileService(profileRepository);

        ValidationException exception = assertThrows(ValidationException.class, () -> profileService.validateAndSaveProfile(driverProfile));
        assertEquals("Email is invalid", exception.getMessage());

        verifyNoInteractions(profileRepository);
    }
}