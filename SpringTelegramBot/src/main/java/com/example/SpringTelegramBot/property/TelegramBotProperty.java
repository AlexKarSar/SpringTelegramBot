package com.example.SpringTelegramBot.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
public class TelegramBotProperty {
    @Value("${bot.name}")
    private String name;
    @Value("${bot.key}")
    private String token;
}
