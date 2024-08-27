package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderStatus implements EnumClass<String> {

    NEW("New"),
    ACCEPTED("Accepted"),
    CONFIRMED("Confirmed"),
    PENDING_PAYMENT("Pending payment"),
    PAID("Paid"),
    READY_FOR_PICKUP("Ready for pickup"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private String id;

    OrderStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(String id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}