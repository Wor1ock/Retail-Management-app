package com.company.intership.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "INTERSHIP_PRICE_HISTORY")
@Entity(name = "intership_PriceHistory")
public class PriceHistory extends StandardEntity {
    private static final long serialVersionUID = -2176950837829876414L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    @NotNull
    private ProductInStore product;

    @NotNull
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @NotNull
    @Column(name = "DATE_", nullable = false)
    private LocalDate date;

    @Column(name = "OLD_PRICE")
    private BigDecimal oldPrice;

    @NotNull
    @Column(name = "NEW_PRICE", nullable = false)
    private BigDecimal newPrice;

    public void setProduct(ProductInStore product) {
        this.product = product;
    }

    public ProductInStore getProduct() {
        return product;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setPriceHistoryDate(LocalDate date) {
        this.date = date;
    }
}