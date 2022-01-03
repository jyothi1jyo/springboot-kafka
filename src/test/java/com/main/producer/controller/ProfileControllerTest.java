package com.main.producer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.producer.model.Addresses;
import com.main.producer.model.Email1;
import com.main.producer.model.Phones;
import com.main.producer.model.Profile;
import com.main.producer.service.ProfileService;

import lombok.SneakyThrows;

@WebMvcTest
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    private static ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void createRecord() {
            Profile profile = getProfile();
            Mockito.when(profileService.save(ArgumentMatchers.any())).thenReturn(profile);
            String json = mapper.writeValueAsString(profile);
            mockMvc.perform(post("/rest/profile").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Test")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("Test1")));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getAllProfiles() throws Exception {
        List<Profile> profile = List.of(getProfile());
        Mockito.when(profileService.getAllProfiles()).thenReturn(profile);
        mockMvc.perform(get("/rest/profiles")).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.equalTo("Test")));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getAllProfilesNoContent() throws Exception {
        List<Profile> profile = List.of(new Profile());
        Mockito.when(profileService.getAllProfiles()).thenReturn(profile);
        mockMvc.perform(get("/rest/profiles")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getProfileById() throws Exception {
        Mockito.when(profileService.getProfileById(ArgumentMatchers.anyString())).thenReturn(Optional.of(getProfile()));
        mockMvc.perform(get("/rest/profile/1"))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getProfileByIdNotFound() throws Exception {
        mockMvc.perform(get("/rest/profile/1"))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void updateProfile() throws Exception{
        String json = mapper.writeValueAsString(getProfile());
        Mockito.when(profileService.getProfileById(ArgumentMatchers.anyString())).thenReturn(Optional.of(getProfile()));
        mockMvc.perform(put("/rest/profile/1")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void update() throws Exception {
        String json = mapper.writeValueAsString(getProfile());
        Mockito.when(profileService.getProfileById(ArgumentMatchers.anyString())).thenReturn(Optional.of(getProfile()));
        mockMvc.perform(patch("/rest/profile/1")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void deleteProfile() throws Exception {
        profileService.deleteById(ArgumentMatchers.anyString());
        mockMvc.perform(delete("/rest/profile/1"))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }

    private Profile getProfile() {
        Profile profileData = new Profile();
        profileData.setFirstName("Test");
        profileData.setLastName("Test1");
        profileData.setGenderBio("M");
        profileData.setBirthdate(LocalDate.parse("2003-11-11"));
        Addresses address = new Addresses("address", "testcity", "state", "zip", "test");
        profileData.setAddresses(address);
        Email1 email1 = new Email1("temp@gmail.com", "user");
        profileData.setEmail1(email1);
        Phones phones = new Phones("01", "123-456-7890", "test", true);
        profileData.setPhones(phones);
        return profileData;
    }
}