package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;

public interface OnlineOrderService {
    String NAME = "intership_OnlineOrderService";

    void updateProductQuantities(OnlineOrder onlineOrder);
    void setCanceledStatus(OnlineOrder onlineOrder);
}