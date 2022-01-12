package com.main.producer.service;

import com.main.producer.model.Addresses;
import com.main.producer.model.Email1;
import com.main.producer.model.Phones;
import com.main.producer.model.Profile;
import com.main.producer.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class ProfileService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ProfileService.class);


    @Autowired
    ProfileRepository profileRepository;

    //@CachePut(value="profiles", key = "#profile.id")
    public Profile save(Profile profile) {
       return profileRepository.save(profile);
    }
    @Cacheable(cacheNames = "profiles")
    public List<Profile> getAllProfiles() {
    	LOGGER.info("into db");
        return profileRepository.findAll();
    }
    @Cacheable(cacheNames = "profiles", key = "#id", unless="#result == null")
    public Optional<Profile> getProfileById(String id) {
        LOGGER.info("into db");
        return profileRepository.findById(id);
    }

    @CacheEvict(cacheNames = "profiles", key = "#id", allEntries = true)
    public void deleteById(String id) {

        profileRepository.deleteById(id);
    }
}