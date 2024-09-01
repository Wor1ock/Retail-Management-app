package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;
import java.util.EnumSet;


public enum OrderStatus implements EnumClass<Integer> {

    NEW(10), ACCEPTED(20), CONFIRMED(30), PENDING_PAYMENT(40), PAID(50), READY_FOR_PICKUP(60), COMPLETED(70), CANCELED(80);

    private final Integer id;

    public static final EnumSet<OrderStatus> NEED_TO_INCREASE_AMOUNT = EnumSet.of(PAID, READY_FOR_PICKUP, COMPLETED, CANCELED);

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