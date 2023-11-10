package com.kamegatze.kafka_test;

import com.kamegatze.kafka_test.dto.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final NewTopic topicTest;

    @GetMapping("/send")
    public void send(@RequestParam String message) {
        Test test = Test.builder()
                .id(1)
                .message(message)
                .name("Hello")
                .build();

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicTest.name(), test);

        future.whenComplete((result, exception) -> {
           if(exception == null) {
               log.info("Sent message = [{}] with offset = [{}]", test, result.getRecordMetadata().offset());
           } else {
               log.info("Unable to send message = [{}] due to: {}", test, exception.getMessage());
           }
        });
    }
}
