package com.company.intership.service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.ProductManufacturer;
import com.company.intership.entity.Store;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TypedQuery;
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
        String query = "select p from intership_ProductInStore p " +
                "where p.store.id = :storeId " +
                "and p.product.manufacturer = :manufacturer " +
                "and (p.quantity = 0 or p.quantity < :threshold)";
        TypedQuery<ProductInStore> typedQuery = entityManager.createQuery(query, ProductInStore.class);
        typedQuery.setViewName("productInStore-view");
        typedQuery.setParameter("storeId", storeId);
        typedQuery.setParameter("manufacturer", manufacturer);
        typedQuery.setParameter("threshold", threshold);

        List<ProductInStore> result = typedQuery.getResultList();
        return result;
    }

    @Override
    @Transactional
    public List<ProductInStore> getProductsWithLowQuantityFromAllStores(ProductManufacturer manufacturer, int threshold) {
        EntityManager entityManager = persistence.getEntityManager();
        String query = "select p from intership_ProductInStore p " +
                "where p.product.manufacturer = :manufacturer " +
                "and (p.quantity = 0 or p.quantity < :threshold)";
        TypedQuery<ProductInStore> typedQuery = entityManager.createQuery(query, ProductInStore.class);
        typedQuery.setViewName("productInStore-view");
        typedQuery.setParameter("manufacturer", manufacturer);
        typedQuery.setParameter("threshold", threshold);

        List<ProductInStore> result = typedQuery.getResultList();
        return result;
    }

    @Override
    @Transactional
    public List<Store> getStoresWithoutProduct(Product product) {
        EntityManager entityManager = persistence.getEntityManager();
        String query = "select s from intership_Store s " +
                "where not exists (select 1 from intership_ProductInStore p " +
                "where p.store.id = s.id " +
                "and p.product = :product and " +
                "(p.quantity = 0 or p.quantity > 0))";
        TypedQuery<Store> typedQuery = entityManager.createQuery(query, Store.class);
        typedQuery.setViewName("store-view");
        typedQuery.setParameter("product", product);

        List<Store> result = typedQuery.getResultList();
        return result;
    }

}