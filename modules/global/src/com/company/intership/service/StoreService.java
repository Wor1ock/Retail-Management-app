package com.company.intership.service;

import com.company.intership.entity.Purchase;

import java.util.List;
import java.util.UUID;

public interface StoreService {
    String NAME = "intership_StoreService";
    /**
     * Возвращает список покупок в указанном магазине.
     * Метод делегирует вызов бизнес-логики бину среднего слоя.
     *
     * @param storeId идентификатор магазина, для которого нужно получить список покупок
     * @return список покупок, сделанных в указанном магазине
     */
    List<Purchase> getPurchasesByStoreId(UUID storeId);

    /**
     * Возвращает список покупок по всем магазинам в указанной торговой сети.
     * Метод делегирует вызов бизнес-логики бину среднего слоя.
     *
     * @param tradeNetworkId идентификатор торговой сети, для которой нужно получить список покупок
     * @return список покупок, сделанных во всех магазинах указанной торговой сети
     */
    List<Purchase> getPurchasesByTradeNetworkId(UUID tradeNetworkId);

}