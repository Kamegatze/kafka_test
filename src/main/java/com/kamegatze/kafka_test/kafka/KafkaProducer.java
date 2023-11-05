package com.kamegatze.kafka_test.kafka;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {

        LOGGER.info(String.format("Message sent %s", message));

        kafkaTemplate.send("javaGuides", message);

    }
}
