package com.intuit.driver.profile.repository;

import com.intuit.driver.profile.models.DriverProfileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProfileRepository
        extends ReactiveCrudRepository<DriverProfileEntity, String> {

    Mono<DriverProfileEntity> save(DriverProfileEntity entity);
}
