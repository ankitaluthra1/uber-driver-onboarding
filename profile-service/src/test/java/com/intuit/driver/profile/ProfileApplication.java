package com.intuit.driver.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class ProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

}