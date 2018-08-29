package org.telegrambot.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "shop", schema = "public", catalog = "telegramdb")
public class Shop {
    private Long id;
    private String name;
    private Collection<Discount> discountsById;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (id != null ? !id.equals(shop.id) : shop.id != null) return false;
        if (name != null ? !name.equals(shop.name) : shop.name != null) return false;
        return discountsById != null ? discountsById.equals(shop.discountsById) : shop.discountsById == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (discountsById != null ? discountsById.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "shopByShopId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Discount> getDiscountsById() {
        return discountsById;
    }

    public void setDiscountsById(Collection<Discount> discountsById) {
        this.discountsById = discountsById;
    }
}
