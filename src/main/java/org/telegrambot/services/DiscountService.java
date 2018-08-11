package org.telegrambot.services;

import org.telegrambot.dao.DiscountDAOImpl;
import org.telegrambot.models.Discount;

import java.util.List;

public class DiscountService {
    private DiscountDAOImpl discountDao = new DiscountDAOImpl();

    public DiscountService() {
    }

    public Discount findDiscount(int id) {
        return discountDao.findById(id);
    }

    public void saveDiscount(Discount discount) {
        discountDao.save(discount);
    }

    public void deleteDiscount(Discount discount) {
        discountDao.delete(discount);
    }

    public void updateDiscount(Discount discount) {
        discountDao.update(discount);
    }

    public List<Discount> findAllDiscounts() {
        return discountDao.findAll();
    }
}
