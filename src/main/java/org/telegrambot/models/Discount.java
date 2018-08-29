package org.telegrambot.models;

import javax.persistence.*;

@Entity
@Table(name = "discount", schema = "public", catalog = "telegramdb")
public class Discount {
    private Long id;
    private String name;
    private Integer oldPrice;
    private Integer newPrice;
    private Shop shopByShopId;

    public Discount() {
    }

    public Discount(String name, Integer oldPrice, Integer newPrice, Shop shop) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.shopByShopId = shop;
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

    @Column(name = "old_price")
    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    @Column(name = "new_price")
    public Integer getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Integer newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (id != null ? !id.equals(discount.id) : discount.id != null) return false;
        if (name != null ? !name.equals(discount.name) : discount.name != null) return false;
        if (oldPrice != null ? !oldPrice.equals(discount.oldPrice) : discount.oldPrice != null) return false;
        if (newPrice != null ? !newPrice.equals(discount.newPrice) : discount.newPrice != null) return false;
        return shopByShopId != null ? shopByShopId.equals(discount.shopByShopId) : discount.shopByShopId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (oldPrice != null ? oldPrice.hashCode() : 0);
        result = 31 * result + (newPrice != null ? newPrice.hashCode() : 0);
        result = 31 * result + (shopByShopId != null ? shopByShopId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    public Shop getShopByShopId() {
        return shopByShopId;
    }

    public void setShopByShopId(Shop shopByShopId) {
        this.shopByShopId = shopByShopId;
    }
}
