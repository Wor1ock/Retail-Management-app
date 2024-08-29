package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderStatus implements EnumClass<Integer> {

    NEW(10),
    ACCEPTED(20),
    CONFIRMED(30),
    PENDING_PAYMENT(40),
    PAID(50),
    READY_FOR_PICKUP(60),
    COMPLETED(70),
    CANCELED(80);

    private Integer id;

    OrderStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(Integer id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}