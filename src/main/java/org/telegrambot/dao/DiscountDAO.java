package org.telegrambot.dao;

import org.telegrambot.models.Discount;

import java.util.List;

public interface DiscountDAO {
    Discount findById(Long id);

    void save(Discount shop);

    void update(Discount shop);

    void delete(Discount shop);

    List<Discount> findAll();
}
