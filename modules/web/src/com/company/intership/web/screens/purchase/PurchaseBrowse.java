package com.company.intership.web.screens.purchase;

import com.company.intership.entity.*;
import com.company.intership.web.screens.onlineorder.OnlineOrderEdit;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("intership_Purchase.browse")
@UiDescriptor("purchase-browse.xml")
@LookupComponent("purchasesTable")
@LoadDataBeforeShow
public class PurchaseBrowse extends StandardLookup<Purchase> {

    @Inject
    private GroupTable<Purchase> purchasesTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Metadata metadata;

    @Subscribe("createBtn.createPurchase")
    public void onCreateBtnCreatePurchase(Action.ActionPerformedEvent event) {
        Purchase purchase = metadata.create(Purchase.class);
        showCreateEditorForPurchase(purchase);
    }

    @Subscribe("createBtn.createOnlineOrder")
    public void onCreateBtnCreateOnlineOrder(Action.ActionPerformedEvent event) {
        OnlineOrder order = metadata.create(OnlineOrder.class);
        showCreateEditorForPurchase(order);
    }

    private void showCreateEditorForPurchase(Purchase purchase) {
        screenBuilders.editor(purchasesTable)
                .editEntity(purchase)
                .build()
                .show();
    }
}