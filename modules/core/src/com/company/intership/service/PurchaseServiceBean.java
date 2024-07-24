package com.company.intership.service;

import com.company.intership.core.PurchaseGetter;
import com.company.intership.entity.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(PurchaseService.NAME)
public class PurchaseServiceBean implements PurchaseService {
    @Inject
    private PurchaseGetter purchaseGetter;

    @Override
    @Transactional
    public List<Purchase> getPurchasesByStoreId(UUID storeId) {
        return purchaseGetter.getPurchasesByStoreId(storeId);
    }
}