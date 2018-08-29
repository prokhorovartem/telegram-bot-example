package org.telegrambot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegrambot.models.Discount;
import org.telegrambot.models.Shop;
import org.telegrambot.services.DiscountService;
import org.telegrambot.services.ShopService;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        ShopService shopService = new ShopService();
        DiscountService discountService = new DiscountService();

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
        if (txt.equals("/dixie")) {
            for (Discount discount : discountService.findAllDiscounts()) {
                sendMsg(msg, "Магазин: " + discount.getShopByShopId().getName() + "\n" + discount.getName() + "\nСтарая цена: " + discount.getOldPrice() + "\nНовая цена: " + discount.getNewPrice());
            }
        }
        if (txt.equals("/sync")) {
            //Удаление всех записей в бд с таблиц shop & discount
            shopService.deleteShops();

            //Парсинг с сайта Дикси и добавление данных в бд
            Shop dixie = new Shop("Дикси");
            shopService.saveShop(dixie);
            Document document = null;
            try {
                document = Jsoup.connect("https://dixy.ru/akcii/skidki-nedeli/?SHOWALL_1=1").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert document != null;
            Elements divElements = document.getElementsByAttributeValue("class", "elem-product");

            divElements.forEach(divElement -> {
                String name = divElement.getElementsByAttributeValue("class", "product-name js-ellipsis").text();
                String oldString = divElement.getElementsByAttributeValue("class", "price-full__integer").text();
                Integer oldPrice = (oldString.equals("")) ? null : Integer.parseInt(oldString);
                Integer newPrice = Integer.parseInt(divElement.getElementsByAttributeValue("class", "price-left").text());
                discountService.saveDiscount(new Discount(name, oldPrice, newPrice, dixie));
            });
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