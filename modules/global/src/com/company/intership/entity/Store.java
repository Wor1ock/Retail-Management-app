package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "INTERSHIP_STORE")
@Entity(name = "intership_Store")
@NamePattern("%s - %s |name, number")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -6415346646198792417L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    private String number;

    @OneToMany(mappedBy = "store")
    private List<ProductInStore> productsInStore;

    @Column(name = "STORE_TYPE")
    private String storeType;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "building", column = @Column(name = "ADDRESS_BUILDING"))
    })
    private Address address;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRADE_NETWORK_ID")
    private TradeNetwork tradeNetwork;

    public StoreType getStoreType() {
        return storeType == null ? null : StoreType.fromId(storeType);
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType == null ? null : storeType.getId();
    }

    public List<ProductInStore> getProductsInStore() {
        return productsInStore;
    }

    public void setProductsInStore(List<ProductInStore> productsInStore) {
        this.productsInStore = productsInStore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public TradeNetwork getTradeNetwork() {
        return tradeNetwork;
    }

    public void setTradeNetwork(TradeNetwork tradeNetwork) {
        this.tradeNetwork = tradeNetwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}