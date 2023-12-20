package com.example.SpringTelegramBot.controller;

import com.example.SpringTelegramBot.request.AddRequest;
import com.example.SpringTelegramBot.request.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SendController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*@GetMapping("/send")
    public void send() {
        kafkaTemplate.send("test", "read this");
    }*/

    public boolean auth(Long chatId ,String username, String password) {
        return new RestTemplate().postForEntity("http://localhost:8070/auth", new AuthRequest(username, password, chatId), String.class).toString().equals("true");
    }

    public void add(Long chatId, String input) {
        new RestTemplate().postForEntity("http://localhost:8085/user-service/add", new AddRequest(chatId, input), void.class);
    }

    public void registration(Long chatId, String username, String password) {
        new RestTemplate().postForEntity("http://localhost:8070/register", new AuthRequest(username, password, chatId), String.class);
    }

    public String get(Long chatId) {
        return String.valueOf(new RestTemplate().postForEntity("http://localhost:8085/user-service/get", new AddRequest(chatId, null), String.class));
    }
}

