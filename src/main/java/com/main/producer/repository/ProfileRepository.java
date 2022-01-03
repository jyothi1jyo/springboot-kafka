package com.main.producer.repository;

import com.main.producer.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfileRepository extends MongoRepository<Profile, String> {
}