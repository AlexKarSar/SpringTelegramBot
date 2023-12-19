package com.example.SpringTelegramBot.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SendController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public void send() {
        kafkaTemplate.send("test", "test");
    }

}

