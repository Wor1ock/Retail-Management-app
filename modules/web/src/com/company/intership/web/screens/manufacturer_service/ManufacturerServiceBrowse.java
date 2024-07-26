package com.company.intership.web.screens.manufacturer_service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.ProductManufacturer;
import com.company.intership.entity.Store;
import com.company.intership.service.ProductManufacturerService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_ManufacturerServiceBrowse")
@UiDescriptor("manufacturer-service-browse.xml")
public class ManufacturerServiceBrowse extends Screen {
    @Inject
    private ProductManufacturerService productManufacturerService;
    @Inject
    private CollectionContainer<ProductInStore> productInStoresDc;
    @Inject
    private CollectionContainer<ProductInStore> productInStoresDc2;
    @Inject
    private CollectionContainer<Store> StoresDc3;
    @Inject
    private PickerField<Store> storeField;
    @Inject
    private PickerField<ProductManufacturer> manufacturerField;
    @Inject
    private TextField<Integer> thresholdField;
    @Inject
    private PickerField<ProductManufacturer> manufacturerField2;
    @Inject
    private TextField<Integer> thresholdField2;
    @Inject
    private PickerField<Product> productField3;
    @Inject
    private Notifications notifications;

    private void showNoRecordsFoundNotification() {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Записей по данному запросу не найдено")
                .show();
    }

    @Subscribe("productInStoresTable1.createTable")
    public void onProductInStoresCreateTable1(Action.ActionPerformedEvent event) {
        List<ProductInStore> items = productManufacturerService.getProductsWithLowQuantity(
                manufacturerField.getValue(),
                storeField.getValue().getId(),
                thresholdField.getValue());

        if (items.isEmpty()) {
            showNoRecordsFoundNotification();
        } else {
            productInStoresDc.setItems(items);
        }
    }

    @Subscribe("productInStoresTable2.createTable")
    public void onProductInStoresCreateTable2(Action.ActionPerformedEvent event) {
        List<ProductInStore> items = productManufacturerService.getProductsWithLowQuantityFromAllStores(
                manufacturerField2.getValue(),
                thresholdField2.getValue());

        if (items.isEmpty()) {
            showNoRecordsFoundNotification();
        } else {
            productInStoresDc2.setItems(items);
        }
    }

    @Subscribe("StoresTable3.createTable")
    public void onProductInStoresCreateTable3(Action.ActionPerformedEvent event) {
        List<Store> items = productManufacturerService.getStoresWithoutProduct(productField3.getValue());

        if (items.isEmpty()) {
            showNoRecordsFoundNotification();
        } else {
            StoresDc3.setItems(items);
        }
    }

}