package com.company.intership.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "INTERSHIP_PURCHASE")
@Entity(name = "intership_Purchase")
public class Purchase extends StandardEntity {
    private static final long serialVersionUID = 8169769622421857829L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @OneToMany(mappedBy = "purchase")
    private List<ProductInPurchase> productsInPurchase;

    public List<ProductInPurchase> getProductsInPurchase() {
        return productsInPurchase;
    }

    public void setProductsInPurchase(List<ProductInPurchase> productsInPurchase) {
        this.productsInPurchase = productsInPurchase;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}