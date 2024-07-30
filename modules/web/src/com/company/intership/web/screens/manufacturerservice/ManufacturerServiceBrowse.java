package com.company.intership.web.screens.manufacturerservice;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.ProductManufacturer;
import com.company.intership.entity.Store;
import com.company.intership.service.ProductManufacturerService;
import com.haulmont.cuba.core.entity.Entity;
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

    @Subscribe("productInStoresTable1.createTable")
    public void onProductInStoresCreateTable1(Action.ActionPerformedEvent event) {
        if (areFieldsNotEmpty(storeField.getValue(), manufacturerField.getValue(), thresholdField.getValue())) {
            List<ProductInStore> productInStores = productManufacturerService.getProductsWithLowQuantity(manufacturerField.getValue(), storeField.getValue().getId(), thresholdField.getValue());

            showDataOrNotification(productInStores, productInStoresDc);
        } else {
            showEmptyFieldNotification("Manufacturer", "Store", "Threshold");
        }
    }

    @Subscribe("productInStoresTable2.createTable")
    public void onProductInStoresCreateTable2(Action.ActionPerformedEvent event) {
        if (areFieldsNotEmpty(storeField.getValue(), manufacturerField.getValue(), thresholdField.getValue())) {
            List<ProductInStore> productInStores = productManufacturerService.getProductsWithLowQuantityFromAllStores(manufacturerField2.getValue(), thresholdField2.getValue());

            showDataOrNotification(productInStores, productInStoresDc2);
        } else {
            showEmptyFieldNotification("Manufacturer", "Threshold");
        }
    }

    @Subscribe("StoresTable3.createTable")
    public void onProductInStoresCreateTable3(Action.ActionPerformedEvent event) {
        if (areFieldsNotEmpty(storeField.getValue(), manufacturerField.getValue(), thresholdField.getValue())) {
            List<Store> stores = productManufacturerService.getStoresWithoutProduct(productField3.getValue());

            showDataOrNotification(stores, StoresDc3);
        } else {
            showEmptyFieldNotification("Product");
        }
    }

    private boolean areFieldsNotEmpty(Object... fields) {
        for (Object field : fields) {
            if (field == null) {
                return false;
            }
        }
        return true;
    }


    private <T extends Entity> void showDataOrNotification(List<T> items, CollectionContainer<T> table) {
        if (items.isEmpty()) {
            notifications.create(Notifications.NotificationType.TRAY).withCaption("Записей по данному запросу не найдено").show();
        } else {
            table.setItems(items);
        }
    }

    private void showEmptyFieldNotification(String... fieldNames) {
        StringBuilder message = new StringBuilder("Эти поля не заполнены: ");

        for (int i = 0; i < fieldNames.length; i++) {
            message.append(fieldNames[i]);
            if (i < fieldNames.length - 1) {
                message.append(", ");
            }
        }

        notifications.create().withCaption(message.toString()).show();
    }
}