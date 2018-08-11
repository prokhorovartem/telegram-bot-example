package org.telegrambot.services;

import org.telegrambot.dao.ShopDAOImpl;
import org.telegrambot.models.Shop;

import java.util.List;

public class ShopService {
    private ShopDAOImpl shopDao = new ShopDAOImpl();

    public ShopService() {
    }

    public Shop findShop(int id) {
        return shopDao.findById(id);
    }

    public void saveShop(Shop shop) {
        shopDao.save(shop);
    }

    public void deleteShop(Shop shop) {
        shopDao.delete(shop);
    }

    public void updateShop(Shop shop) {
        shopDao.update(shop);
    }

    public List<Shop> findAllShops() {
        return shopDao.findAll();
    }
}
