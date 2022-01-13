package com.main.producer.controller;

import com.main.producer.exception.ResourceNotFoundException;
import com.main.producer.model.*;
import com.main.producer.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class ProfileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	ProfileService profileService;

	@PostMapping("/profile")
	public ResponseEntity<Profile> createRecord(@RequestBody @Valid Profile profile) {

		LOGGER.info("ProfileController In createRecord");

		Profile _profile = profileService.save(profile);
		return new ResponseEntity<>(_profile, HttpStatus.CREATED);

	}

	@GetMapping("/profiles")
	public ResponseEntity<List<Profile>> getAllProfiles() {

		LOGGER.info("ProfileController In getAllProfiles");

		List<Profile> profiles = new ArrayList<Profile>();

		profileService.getAllProfiles().forEach(profiles::add);

		if (profiles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}

	@GetMapping("/profile/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable("id") String id) {

		LOGGER.info("ProfileController In getProfileById");

		Profile profileData = profileService.getProfileById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found profile with id = " + id));
		return new ResponseEntity<>(profileData, HttpStatus.OK);

	}

	@PutMapping("/profile/{id}")
	public ResponseEntity<Profile> updateProfile(@PathVariable("id") String id, @RequestBody Profile profile) {

		LOGGER.info("ProfileController In updateProfile");

		Profile profileData = profileService.getProfileById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found profile with id = " + id));
		Profile _profile = getEditData(profileData, profile);
		return new ResponseEntity<>(profileService.save(_profile), HttpStatus.OK);

	}

	@PatchMapping("/profile/{id}")
	public ResponseEntity<Profile> update(@PathVariable("id") String id, @RequestBody Profile profile) {

		LOGGER.info("ProfileController In update");

		Profile profileData = profileService.getProfileById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found profile with id = " + id));
		Profile _profile = getEditData(profileData, profile);
		return new ResponseEntity<>(profileService.save(_profile), HttpStatus.OK);

	}

	private Profile getEditData(Profile profileData, Profile profile) {
		profileData.setFirstName(profile.getFirstName());
		profileData.setLastName(profile.getLastName());
		profileData.setGenderBio(profile.getGenderBio());
		profileData.setBirthdate(profile.getBirthdate());
		profileData.setPreferred_mode_of_contact(profile.getPreferred_mode_of_contact());
		Addresses address = profile.getAddresses();
		profileData.setAddresses(address);
		Email1 email1 = profile.getEmail1();
		profileData.setEmail1(email1);
		Email2 email2 = profile.getEmail2();
		profileData.setEmail2(email2);
		Phones phones = profile.getPhones();
		profileData.setPhones(phones);
		return profileData;
	}

	@DeleteMapping("/profile/{id}")
	public ResponseEntity<String> deleteProfile(@PathVariable("id") String id) {

		LOGGER.info("ProfileController In deleteProfile");
		Profile profileData = profileService.deleteById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found profile with id = " + id));
		return new ResponseEntity<>("Record Deleted successfully with ID = "+profileData.getId(), HttpStatus.OK);

	}
}