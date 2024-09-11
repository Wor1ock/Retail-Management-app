package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;
import java.util.EnumSet;


public enum OrderStatus implements EnumClass<String> {

    NEW("New"), ACCEPTED("Acccepted"), CONFIRMED("Confirmed"), PENDING_PAYMENT("Pending payment"), PAID("Paid"), READY_FOR_PICKUP("Ready for pickup"), COMPLETED("Completed"), CANCELED("Canceled");

    private final String id;

    public static final EnumSet<OrderStatus> NEED_TO_INCREASE_AMOUNT = EnumSet.of(PAID, READY_FOR_PICKUP, COMPLETED, CANCELED);

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