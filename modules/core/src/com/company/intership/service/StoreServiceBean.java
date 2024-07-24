package com.company.intership.service;

import com.company.intership.core.PurchaseGetter;
import com.company.intership.entity.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(StoreService.NAME)
public class StoreServiceBean implements StoreService {
    @Inject
    private PurchaseGetter purchaseGetter;

    @Transactional
    @Override
    public List<Purchase> getPurchasesByStoreId(UUID storeId) {
        return purchaseGetter.getPurchasesByStoreId(storeId);
    }

    @Transactional
    @Override
    public List<Purchase> getPurchasesByTradeNetworkId(UUID tradeNetworkId) {
        return purchaseGetter.getPurchasesByTradeNetworkId(tradeNetworkId);
    }
}