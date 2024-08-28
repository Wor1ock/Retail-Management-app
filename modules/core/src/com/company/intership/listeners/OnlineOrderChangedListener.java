package com.company.intership.listeners;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.OrderStatus;
import com.google.common.collect.ImmutableMap;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.Transactions;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

@Component("intership_OnlineOrderChangedListener")
public class OnlineOrderChangedListener {
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private TransactionalDataManager transactionalDataManager;
    @Inject
    private Logger log;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    private static final String PROCESS_CODE = "online-order";
    @Inject
    private Transactions transactions;
    @Inject
    private Persistence persistence;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<OnlineOrder, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            try (Transaction transaction = persistence.createTransaction()) {
                OnlineOrder editedEntity = transactionalDataManager
                        .load(event.getEntityId())
                        .view("online-order-view")
                        .one();
                String sequenceName = "orderNumberSequence";
                String nextNumber = String.valueOf(uniqueNumbersAPI.getNextNumber(sequenceName));

                editedEntity.setOrderNumber(nextNumber);
                editedEntity.setStatus(OrderStatus.NEW);
                transactionalDataManager.save(editedEntity);
                log.info("Online order created with number {}", editedEntity.getOrderNumber());

                User user = (editedEntity.getCustomer() != null)
                        ? editedEntity.getCustomer().getExtendedUser()
                        : userSessionSource.getUserSession().getCurrentOrSubstitutedUser();

                Map<String, Object> processParams = ImmutableMap.of(
                        "onlineOrder", editedEntity,
                        "customer", user
                );

                bprocRuntimeService.startProcessInstanceByKey(
                        PROCESS_CODE,
                        "2",
                        processParams
                );

                transaction.commit();
            }
        }
    }
}