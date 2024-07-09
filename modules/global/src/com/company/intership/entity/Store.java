package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "INTERSHIP_STORE")
@Entity(name = "intership_Store")
@NamePattern("%s|name")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -6415346646198792417L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    private String number;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRADE_NETWORK_ID")
    private TradeNetwork tradeNetwork;

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