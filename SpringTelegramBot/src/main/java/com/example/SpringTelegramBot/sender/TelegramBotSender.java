package com.example.SpringTelegramBot.sender;


import com.example.SpringTelegramBot.property.TelegramBotProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotSender extends DefaultAbsSender {

    public TelegramBotSender(TelegramBotProperty property) {
        super(new DefaultBotOptions(), property.getToken());
    }

    public Message sendMessageBy(Long chatId, Integer messageId, String text) throws TelegramApiException {
        return execute(SendMessage.builder().chatId(chatId)
                .replyToMessageId(messageId)
                .text(text)
                .build());
    }
}
