package com.main.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.producer.constant.ApplicationConstant;
import com.main.producer.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProfileConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProfileConsumer.class);

    @Autowired
    ProfileService profileService;   


    @KafkaListener(groupId = ApplicationConstant.GROUP_ID_JSON, topics = ApplicationConstant.TOPIC_NAME, containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedProfileData(Profile profile) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(profile);
        profileService.save(profile);
        logger.info("Json message received using Kafka listener " + jsonString);
    }

}