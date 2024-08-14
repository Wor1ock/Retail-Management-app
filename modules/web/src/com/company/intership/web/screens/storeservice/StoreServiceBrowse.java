package com.company.intership.web.screens.storeservice;

import com.company.intership.entity.Purchase;
import com.company.intership.entity.Store;
import com.company.intership.entity.TradeNetwork;
import com.company.intership.service.StoreService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_StoreServiceBrowse")
@UiDescriptor("store-service-browse.xml")
public class StoreServiceBrowse extends Screen {
    @Inject
    private StoreService storeService;
    @Inject
    private Notifications notifications;
    @Inject
    private PickerField<Store> storeField;
    @Inject
    private CollectionContainer<Purchase> purchasesByTradeNetworkDc;
    @Inject
    private PickerField<TradeNetwork> tradeNetworkField;
    @Inject
    private CollectionContainer<Purchase> purchasesByStoreDc;

    @Subscribe("purchasesByStoreTable.createTable")
    public void onPurchasesByStoreTable(Action.ActionPerformedEvent event) {
        if (areFieldsNotEmpty(storeField.getValue())) {
            List<Purchase> purchases = storeService.getPurchasesByStoreId(storeField.getValue().getId());
            showDataOrNotification(purchases, purchasesByStoreDc);
        } else {
            showEmptyFieldNotification("Store");
        }
    }

    @Subscribe("purchasesByTradeNetworkTable.createTable")
    public void onPurchasesByTradeNetworkTable(Action.ActionPerformedEvent event) {
        if (areFieldsNotEmpty(tradeNetworkField.getValue())) {
            List<Purchase> purchases = storeService.getPurchasesByTradeNetworkId(tradeNetworkField.getValue().getId());
            showDataOrNotification(purchases, purchasesByTradeNetworkDc);
        } else {
            showEmptyFieldNotification("Trade Network");
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

    private void showDataOrNotification(List<Purchase> items, CollectionContainer<Purchase> table) {
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
