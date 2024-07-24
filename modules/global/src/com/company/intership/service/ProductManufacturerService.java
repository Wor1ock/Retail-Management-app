package com.company.intership.service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.ProductManufacturer;
import com.company.intership.entity.Store;

import java.util.List;
import java.util.UUID;

public interface ProductManufacturerService {
    String NAME = "intership_ProductManufacturerService";

    /**
     * Возвращает список товаров данного производителя из определённого магазина,
     * количество которых меньше заданного порога.
     * Если количество товара 0 (а число в переданном параметре больше 0),
     * то такой товар должен попасть в результат.
     * Если записи с таким товаром в магазине вообще нет, он не должен попасть в результат.
     *
     * @param manufacturer производитель, товары которого нужно найти
     * @param storeId идентификатор магазина
     * @param threshold пороговое значение количества товара
     * @return список товаров в магазине
     */
    List<ProductInStore> getProductsWithLowQuantity(ProductManufacturer manufacturer, UUID storeId, int threshold);

    /**
     * Возвращает список товаров данного производителя из всех магазинов,
     * количество которых меньше заданного порога.
     *
     * @param manufacturer производитель, товары которого нужно найти
     * @param threshold пороговое значение количества товара
     * @return список товаров в магазинах
     */
    List<ProductInStore> getProductsWithLowQuantityFromAllStores(ProductManufacturer manufacturer, int threshold);

    /**
     * Возвращает список магазинов, в котором не продается определённый товар.
     * Если существует запись Товар в магазине, но её количество равно нулю,
     * то это считается, что такой товар продаётся в магазине, а значит не должен попасть в результат.
     *
     * @param product товар, который нужно проверить
     * @return список магазинов, где товар не продаётся
     */
    List<Store> getStoresWithoutProduct(Product product);
}