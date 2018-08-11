package org.telegrambot.dao;

import org.telegrambot.models.Shop;

import java.util.List;

public interface ShopDAO {
    Shop findById(int id);

    void save(Shop shop);

    void update(Shop shop);

    void delete(Shop shop);

    List<Shop> findAll();
}
