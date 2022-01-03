package com.main.producer.controller;

import com.main.producer.model.Profile;
import com.main.producer.service.ProfileProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/kafka")
public class ProfileProducerController {

	private final ProfileProducerService profileProducerService;

    public ProfileProducerController(ProfileProducerService profileProducerService) {
        this.profileProducerService = profileProducerService;
    }
    
    @PostMapping("/profile")
    public Profile createRecord(@RequestBody @Valid Profile profile) {
        return profileProducerService.save(profile);
    }   
}