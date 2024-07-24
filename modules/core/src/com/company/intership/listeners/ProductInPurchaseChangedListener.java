package com.company.intership.listeners;

import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("intership_ProductInPurchaseChangedListener")
public class ProductInPurchaseChangedListener {
    @Inject
    private TransactionalDataManager transactionalDataManager;
    private static final Logger log = LoggerFactory.getLogger(ProductInPurchaseChangedListener.class);

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<ProductInPurchase, UUID> beforeCommitEvent) {
        if (!beforeCommitEvent.getType().equals(EntityChangedEvent.Type.DELETED)) {
            ProductInPurchase editedProductInPurchase = transactionalDataManager.load(beforeCommitEvent.getEntityId()).view("productInPurchase-view").one();
            ProductInStore productInStore = editedProductInPurchase.getProductInStore();

            if (editedProductInPurchase.getQuantity() == 0) {
                return;
            } else if (editedProductInPurchase.getQuantity() > productInStore.getQuantity()) {
                productInStore.setQuantity(0);
            } else {
                productInStore.setQuantity(productInStore.getQuantity() - editedProductInPurchase.getQuantity());
                log.info("Decreased quantity of product {} by {}", productInStore.getProduct().getName(), editedProductInPurchase.getQuantity());
            }

            transactionalDataManager.save(productInStore);
            log.info("Added/Updated product in purchase: {}", editedProductInPurchase.getProductInStore().getProduct().getName());
        }
    }

}