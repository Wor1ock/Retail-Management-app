package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.OrderStatus;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import static com.company.intership.entity.OrderStatus.NEED_TO_INCREASE_AMOUNT;

@Service(OnlineOrderService.NAME)
public class OnlineOrderServiceBean implements OnlineOrderService {
    @Inject
    private DataManager dataManager;

    @Override
    public void setStatus(OnlineOrder onlineOrder, String statusString) {
        OrderStatus status = OrderStatus.valueOf(statusString.toUpperCase());
        onlineOrder.setStatus(status);
        dataManager.commit(onlineOrder);
    }

    @Override
    public void setStatusCanceled(OnlineOrder onlineOrder) {
        if (NEED_TO_INCREASE_AMOUNT.contains(onlineOrder.getStatus())) {
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