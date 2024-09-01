package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.OrderStatus;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(OnlineOrderService.NAME)
public class OnlineOrderServiceBean implements OnlineOrderService {
    @Inject
    private DataManager dataManager;

    @Override
    public void setNewStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.NEW);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setAcceptedStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.ACCEPTED);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setConfirmedStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.CONFIRMED);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setPendingPaymentStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.PENDING_PAYMENT);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setPaidStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.PAID);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setReadyForPickupStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.READY_FOR_PICKUP);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setCompletedStatus(OnlineOrder onlineOrder) {
        onlineOrder.setStatus(OrderStatus.COMPLETED);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setCanceledStatus(OnlineOrder onlineOrder) {
        if (OrderStatus.PAID.getId() <= onlineOrder.getStatus().getId()) {
            returnProductsToStore(onlineOrder);
        }
        onlineOrder.setStatus(OrderStatus.CANCELED);
        dataManager.commit(onlineOrder);
    }

    public void returnProductsToStore(OnlineOrder onlineOrder) {
        onlineOrder = dataManager.reload(onlineOrder, "online-order-view");
        for (ProductInPurchase item : onlineOrder.getProductsInPurchase()) {
            ProductInStore productInStore = item.getProductInStore();
            productInStore.setQuantity(productInStore.getQuantity() + item.getQuantity());
            dataManager.commit(productInStore);
        }
    }

    @Override
    public void updateProductQuantities(OnlineOrder onlineOrder) {
        onlineOrder = dataManager.reload(onlineOrder, "online-order-view");
        for (ProductInPurchase item : onlineOrder.getProductsInPurchase()) {
            ProductInStore productInStore = item.getProductInStore();
            if (item.getQuantity() > productInStore.getQuantity()) {
                productInStore.setQuantity(0);
            } else {
                productInStore.setQuantity(productInStore.getQuantity() - item.getQuantity());
            }
            dataManager.commit(productInStore);
        }
    }

}