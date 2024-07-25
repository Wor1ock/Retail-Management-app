package com.company.intership.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "INTERSHIP_PRODUCT_IN_PURCHASE")
@Entity(name = "intership_ProductInPurchase")
@PublishEntityChangedEvents
public class ProductInPurchase extends StandardEntity {
    private static final long serialVersionUID = -6184509418949999198L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    @JoinColumn(name = "PRODUCT_ID")
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private ProductInStore productInStore;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public void setProductInStore(ProductInStore productInStore) {
        this.productInStore = productInStore;
    }

    public ProductInStore getProductInStore() {
        return productInStore;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}