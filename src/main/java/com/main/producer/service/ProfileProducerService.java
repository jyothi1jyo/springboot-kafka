package com.main.producer.service;
import com.main.producer.constant.ApplicationConstant;


import com.main.producer.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProfileProducerService {

    //@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
	public ProfileProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Profile save(Profile profile) {
        this.kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, profile);
        return profile;
    }
}