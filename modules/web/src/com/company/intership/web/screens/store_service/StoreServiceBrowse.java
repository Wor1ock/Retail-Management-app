package com.company.intership.web.screens.store_service;

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
        List<Purchase> purchases = storeService
                .getPurchasesByStoreId(storeField.getValue().getId());
        showDataOrNotification(purchases, purchasesByStoreDc);
    }

    @Subscribe("purchasesByTradeNetworkTable.createTable")
    public void onPurchasesByTradeNetworkTable(Action.ActionPerformedEvent event) {
        List<Purchase> purchases = storeService
                .getPurchasesByTradeNetworkId(tradeNetworkField.getValue().getId());
        showDataOrNotification(purchases, purchasesByTradeNetworkDc);
    }

    private void showDataOrNotification(List<Purchase> items, CollectionContainer<Purchase> table) {
        if (items.isEmpty()) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("Записей по данному запросу не найдено")
                    .show();
        } else {
            table.setItems(items);
        }
    }
}