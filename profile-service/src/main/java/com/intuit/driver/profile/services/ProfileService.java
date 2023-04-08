package com.intuit.driver.profile.services;

import com.intuit.driver.profile.models.DriverProfile;
import com.intuit.driver.profile.models.DriverProfileEntity;
import com.intuit.driver.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Mono<String> validateAndSaveProfile(DriverProfile driverProfile) {
        driverProfile.validate();
        return profileRepository.save(DriverProfileEntity.from(driverProfile)).map(e -> e.getId());
    }

}
