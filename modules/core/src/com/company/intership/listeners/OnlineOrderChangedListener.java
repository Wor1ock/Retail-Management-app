package com.company.intership.listeners;

import com.company.intership.entity.OnlineOrder;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("intership_OnlineOrderChangedListener")
public class OnlineOrderChangedListener {
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private TransactionalDataManager transactionalDataManager;
    @Inject
    private Logger log;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<OnlineOrder, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            OnlineOrder editedEntity = transactionalDataManager
                    .load(event.getEntityId())
                    .view("online-order-view")
                    .one();
            String sequenceName = "orderNumberSequence";
            String nextNumber = String.valueOf(uniqueNumbersAPI.getNextNumber(sequenceName));

            editedEntity.setOrderNumber(nextNumber);
            transactionalDataManager.save(editedEntity);

            log.info("Online order created with number {}", editedEntity.getOrderNumber());
        }
    }
}