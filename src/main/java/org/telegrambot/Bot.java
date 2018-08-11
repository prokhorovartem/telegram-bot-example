package org.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegrambot.models.Shop;
import org.telegrambot.services.ShopService;

public class Bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        ShopService shopService = new ShopService();
        Message msg = update.getMessage();
        String txt = msg.getText();
        System.out.println("user" + "(" + msg.getChatId() + "): " + txt);
        if (txt.equals("/start")) {
            sendMsg(msg, "Hello, user! I will try to entertain you.");
        }
        if (txt.equals("/shops")) {
            for (Shop shop : shopService.findAllShops()) {
                sendMsg(msg, shop.getName());
            }
        }

    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
            System.out.println("bot: " + text);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return BotConfig.USERNAME;
    }

    public String getBotToken() {
        return BotConfig.TOKEN;
    }
}
