package org.telegrambot.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.telegrambot.models.Shop;
import org.telegrambot.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ShopDAOImpl implements ShopDAO {
    public Shop findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Shop.class, id);
    }

    public void save(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(shop);
        tx1.commit();
        session.close();
    }

    public void update(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(shop);
        tx1.commit();
        session.close();
    }

    public void delete(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(shop);
        tx1.commit();
        session.close();
    }

    public List<Shop> findAll() {
        return (List<Shop>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Shop").list();
    }
}
