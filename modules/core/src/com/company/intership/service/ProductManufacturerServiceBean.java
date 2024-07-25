package com.company.intership.service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.ProductManufacturer;
import com.company.intership.entity.Store;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(ProductManufacturerService.NAME)
public class ProductManufacturerServiceBean implements ProductManufacturerService {

    @Inject
    private Persistence persistence;

    @Override
    @Transactional
    public List<ProductInStore> getProductsWithLowQuantity(ProductManufacturer manufacturer, UUID storeId, int threshold) {
        EntityManager entityManager = persistence.getEntityManager();
        return entityManager.createQuery(
                        "SELECT p FROM intership_ProductInStore p " +
                                "WHERE p.store.id = :storeId " +
                                "AND p.product.manufacturer = :manufacturer " +
                                "AND (p.quantity = 0 OR p.quantity < :threshold)", ProductInStore.class)
                .setViewName("productInStore-view")
                .setParameter("storeId", storeId)
                .setParameter("manufacturer", manufacturer)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    @Transactional
    public List<ProductInStore> getProductsWithLowQuantityFromAllStores(ProductManufacturer manufacturer, int threshold) {
        EntityManager entityManager = persistence.getEntityManager();
        return entityManager.createQuery(
                        "SELECT p FROM intership_ProductInStore p " +
                                "WHERE p.product.manufacturer = :manufacturer " +
                                "AND (p.quantity = 0 OR p.quantity < :threshold)", ProductInStore.class)
                .setViewName("productInStore-view")
                .setParameter("manufacturer", manufacturer)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Store> getStoresWithoutProduct(Product product) {
        EntityManager entityManager = persistence.getEntityManager();
        return entityManager.createQuery("SELECT s FROM intership_Store s WHERE not exists " +
                        "(SELECT 1 FROM intership_ProductInStore p WHERE p.store.id = s.id " +
                        "AND p.product = :product AND (p.quantity = 0 OR p.quantity > 0))", Store.class)
                .setViewName("store-view")
                .setParameter("product", product)
                .getResultList();
    }
}