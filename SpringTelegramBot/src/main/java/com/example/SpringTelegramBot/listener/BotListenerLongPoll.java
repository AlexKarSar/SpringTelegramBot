package com.example.SpringTelegramBot.listener;


import com.example.SpringTelegramBot.property.TelegramBotProperty;
import com.example.SpringTelegramBot.sender.TelegramBotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BotListenerLongPoll extends TelegramLongPollingBot {

    private final TelegramBotProperty telegramBotProperty;
    private final TelegramBotSender telegramBotSender;

    private String requestProcessing(String input, String username){
        String tmp = "";
        switch (input){
            case "/start" :
                tmp = String.format("""
                Привет %s)!
                
                Этот бот создан для создания заметок и планов
                Для пользования ботом необходимо пройти регистрацию /registration (если вы не были авторизованы на этом телеграмм аккаунте ранее)
                
                Если вы хотите получить доступ к вашим заметкам из другого аккаунта пройдите авторизацию /auth
                
                Список доступных команд
                /check - получить список ваших заметок
                /add - добавить новую заметку
                /complete - удалить одну из заметок
                /complete_all - удалить все заметки
                
                """, username);
                break;
        }
        return tmp;
    }

    public BotListenerLongPoll(TelegramBotProperty property, TelegramBotSender sender) {
        super(property.getToken());
        telegramBotProperty = property;
        telegramBotSender = sender;

        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "получить информацию о боте"));
        listofCommands.add(new BotCommand("/registration", "зарегистрироваться"));
        listofCommands.add(new BotCommand("/check", "получить список ваших заметок"));
        listofCommands.add(new BotCommand("/add", "добавить новую заметку"));
        listofCommands.add(new BotCommand("/complete", "удалить одну из заметок"));
        listofCommands.add(new BotCommand("/complete_all", "удалить все заметки"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            telegramBotSender.sendMessageBy(update.getMessage().getChatId(), update.getMessage().getMessageId(), requestProcessing(update.getMessage().getText() ,update.getMessage().getChat().getFirstName()));
            log.info(String.format("ChatId : %s", update.getMessage().getChatId().toString()));
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotProperty.getName();
    }
}
