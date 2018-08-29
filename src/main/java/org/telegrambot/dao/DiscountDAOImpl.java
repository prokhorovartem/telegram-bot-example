package org.telegrambot.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.telegrambot.models.Discount;
import org.telegrambot.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    public Discount findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Discount.class, id);
    }

    public void save(Discount discount) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(discount);
        tx1.commit();
        session.close();
    }

    public void update(Discount discount) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(discount);
        tx1.commit();
        session.close();
    }

    public void delete(Discount shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(shop);
        tx1.commit();
        session.close();
    }

    public List<Discount> findAll() {
        return (List<Discount>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Discount").list();
    }
}
