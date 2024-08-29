package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;

public interface OnlineOrderService {
    String NAME = "intership_OnlineOrderService";

    void updateProductQuantities(OnlineOrder onlineOrder);
    void setNewStatus(OnlineOrder onlineOrder);
    void setAcceptedStatus(OnlineOrder onlineOrder);
    void setConfirmedStatus(OnlineOrder onlineOrder);
    void setPendingPaymentStatus(OnlineOrder onlineOrder);
    void setPaidStatus(OnlineOrder onlineOrder);
    void setReadyForPickupStatus(OnlineOrder onlineOrder);
    void setCompletedStatus(OnlineOrder onlineOrder);
    void setCanceledStatus(OnlineOrder onlineOrder);
}