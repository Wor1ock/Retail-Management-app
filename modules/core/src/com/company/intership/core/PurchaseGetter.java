package com.company.intership.core;

import com.company.intership.entity.Purchase;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Component(PurchaseGetter.NAME)
public class PurchaseGetter {
    public static final String NAME = "intership_PurchaseGetter";
    @Inject
    private Persistence persistence;

    public List<Purchase> getPurchasesByStoreId(UUID storeId) {
        EntityManager entityManager = persistence.getEntityManager();
        return entityManager.createQuery("SELECT p FROM intership_Purchase p WHERE p.store.id = :storeId", Purchase.class)
                .setParameter("storeId", storeId)
                .setViewName("purchase-view")
                .getResultList();
    }
}