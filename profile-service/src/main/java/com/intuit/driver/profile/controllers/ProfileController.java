package com.intuit.driver.profile.controllers;

import com.intuit.driver.profile.models.DriverProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.intuit.driver.profile.services.ProfileService;

@RestController
@RequestMapping("/profiles/drivers")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(value = "/signup")
    public Mono<String> signUp(@RequestBody DriverProfile driverProfile){
        return profileService.validateAndSaveProfile(driverProfile);
    }

}
