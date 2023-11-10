package com.kamegatze.kafka_test;

import com.kamegatze.kafka_test.config.KafkaConsumerConfig;
import com.kamegatze.kafka_test.dto.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class KafkaTestApplication {

	private final KafkaConsumerConfig config;

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}

	@KafkaListener(topics = "test-topic", groupId = "test")
	public void readMessage(Test test) {
		log.info("Object: {}", test);
	}

}
