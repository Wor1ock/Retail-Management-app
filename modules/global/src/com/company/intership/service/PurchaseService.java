package com.company.intership.service;

import com.company.intership.entity.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {
    String NAME = "intership_PurchaseService";

    /**
     * Возвращает список покупок в указанном магазине.
     * Метод находит все покупки, связанные с данным магазином.
     *
     * @param storeId идентификатор магазина, для которого нужно получить список покупок
     * @return список покупок, сделанных в указанном магазине
     */
    List<Purchase> getPurchasesByStoreId(UUID storeId);
}