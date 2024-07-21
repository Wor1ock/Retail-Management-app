package com.company.intership.listeners;

import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component("intership_ProductInStoreChangedListener")
public class ProductInStoreChangedListener {

    private static final Logger log = LoggerFactory.getLogger(ProductInStoreChangedListener.class);

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<ProductInStore, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            log.info("Handling CREATED event for entity: {}", event.getEntityId());
            commitNewPriceHistory(event.getEntityId(), BigDecimal.ZERO);

        } else if (event.getType().equals(EntityChangedEvent.Type.UPDATED) && event.getChanges().isChanged("price")) {
            log.info("Handling UPDATED event for entity: {}", event.getEntityId());
            BigDecimal oldPrice = event.getChanges().getOldValue("price");
            commitNewPriceHistory(event.getEntityId(), oldPrice);
        }
    }

    private void commitNewPriceHistory(Id<ProductInStore, UUID> id, BigDecimal price) {
        log.info("Committing new PriceHistory for entity ID: {}", id);

        PriceHistory newPriceHistory = metadata.create(PriceHistory.class);
        ProductInStore productInStore = dataManager.load(id)
                .view("productInStore-view")
                .one();
        newPriceHistory.setStore(productInStore.getStore());
        newPriceHistory.setProduct(productInStore);
        newPriceHistory.setPriceHistoryDate(LocalDate.now());
        newPriceHistory.setNewPrice(productInStore.getPrice());
        newPriceHistory.setOldPrice(price);

        dataManager.commit(newPriceHistory);
    }
}
