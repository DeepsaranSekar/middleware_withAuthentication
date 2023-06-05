package com.nuance.middleware.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nuance.middleware.utility.InputOutputParam;

@Service
public class JsonKafkaConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

		@KafkaListener(topics = "${spring.kafka.topics}", groupId = "${spring.kafka.consumer.group-id}")
	   	public void consumeCustomJson(InputOutputParam data){
	        LOGGER.info(String.format("Json message recieved -> %s", data.toString()));
	    }
}
