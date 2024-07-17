package com.company.intership.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "INTERSHIP_PRODUCT_IN_PURCHASE")
@Entity(name = "intership_ProductInPurchase")
public class ProductInPurchase extends StandardEntity {
    private static final long serialVersionUID = -6184509418949999198L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    @JoinColumn(name = "PRODUCT_ID")
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private ProductInStore product;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public void setProduct(ProductInStore product) {
        this.product = product;
    }

    public ProductInStore getProduct() {
        return product;
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