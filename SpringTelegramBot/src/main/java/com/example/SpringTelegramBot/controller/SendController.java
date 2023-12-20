package com.example.SpringTelegramBot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SendController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/get")
    public String get() {
        //kafkaTemplate.send("test", "read this");
        return "str";
    }

    @PostMapping("/add")
    public void add(String str){

    }

    @PostMapping("/registration")
    public boolean registration(String password){
        return true;
    }

    @PostMapping("/auth")
    public boolean auth(String username, String password){
        return true;
    }

}

