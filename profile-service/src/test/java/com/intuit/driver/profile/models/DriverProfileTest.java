package com.intuit.driver.profile.models;


import com.intuit.driver.profile.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DriverProfileTest {

    @Test
    public void testValidDriverProfile() {
        DriverProfile driverProfile = new DriverProfile("Ankita", "Luthra", "some@example.com", "555-555-5555", "1234");
        Assertions.assertDoesNotThrow(() -> driverProfile.validate());
    }

    @Test
    public void testInvalidDriverProfileWithMissingDocumentId() {
        DriverProfile driverProfile = new DriverProfile("Ankita", "Luthra", "some@example.com", "555-555-5555", null);
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> driverProfile.validate());
        Assertions.assertEquals("DocumentId is not present", exception.getMessage());
    }

    @Test
    public void testInvalidDriverProfileWithInvalidEmail() {
        DriverProfile driverProfile = new DriverProfile("Ankita", "Luthra", "something", "555-555-5555", "1234");

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> driverProfile.validate());
        Assertions.assertEquals("Email is invalid", exception.getMessage());
    }

}
