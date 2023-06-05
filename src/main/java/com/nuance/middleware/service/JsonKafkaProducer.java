package com.nuance.middleware.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.nuance.middleware.utility.InputOutputParam;

@Service
public class JsonKafkaProducer {
	
	@Value("${spring.kafka.topics}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, InputOutputParam> kafkaTemplateCustomJson;

    public JsonKafkaProducer(KafkaTemplate<String, InputOutputParam> kafkaTemplateCustomJson) {
        this.kafkaTemplateCustomJson = kafkaTemplateCustomJson;
    }

    public void sendMessage(InputOutputParam data){
        LOGGER.info(String.format("CustomJson Message sent -> %s", data.toString()));

        Message<InputOutputParam> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        kafkaTemplateCustomJson.send(message);
    }
}
