package com.company.intership.web.screens.purchase;

import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Purchase;
import com.company.intership.web.screens.productinpurchase.ProductInPurchaseEdit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.builders.AfterScreenCloseEvent;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@UiController("intership_Purchase.edit")
@UiDescriptor("purchase-edit.xml")
@EditedEntityContainer("purchaseDc")
@LoadDataBeforeShow
public class PurchaseEdit extends StandardEditor<Purchase> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataContext dataContext;
    @Inject
    private Notifications notifications;
    @Inject
    private CollectionContainer<ProductInPurchase> productsInPurchaseDc;
    @Inject
    private Table<ProductInPurchase> productInPurchaseTable;
    @Inject
    private CollectionLoader<ProductInPurchase> productInPurchaseDl;
    private static final Logger log = LoggerFactory.getLogger(PurchaseEdit.class);
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productInPurchaseDl.setParameter("purchaseDc", getEditedEntity());
        productInPurchaseDl.load();
    }

    @Subscribe("productInPurchaseTable.create")
    public void onProductInPurchaseTableCreate(Action.ActionPerformedEvent event) {
        log.info("Creating new ProductInPurchase entity");
        screenBuilders.editor(ProductInPurchase.class, this)
                .newEntity()
                .withInitializer(productInPurchase -> {
                    productInPurchase.setPurchase(getEditedEntity());
                })
                .withScreenClass(ProductInPurchaseEdit.class)
                .withParentDataContext(dataContext)
                .withAfterCloseListener(this::processAfterCloseEvent)
                .build()
                .show();
    }

    @Subscribe("productInPurchaseTable.edit")
    public void onProductInPurchaseTableEdit(Action.ActionPerformedEvent event) {

        if (productInPurchaseTable.getSingleSelected() != null) {
            screenBuilders.editor(ProductInPurchase.class, this)
                    .editEntity(productInPurchaseTable.getSingleSelected())
                    .withInitializer(productInPurchase -> {
                        productInPurchase.setPurchase(getEditedEntity());
                    })
                    .withScreenClass(ProductInPurchaseEdit.class)
                    .withParentDataContext(dataContext)
                    .withAfterCloseListener(this::processAfterCloseEvent)
                    .build()
                    .show();
        }
    }

    private void processAfterCloseEvent(AfterScreenCloseEvent<ProductInPurchaseEdit> afterCloseEvent) {
        if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
            ProductInPurchase editedEntity = afterCloseEvent.getScreen().getEditedEntity();
            ProductInStore productInStore = editedEntity.getProductInStore();

            if (editedEntity.getQuantity() == 0) {
                return;
            }

            if (editedEntity.getQuantity() > productInStore.getQuantity()) {
                editedEntity.setQuantity(productInStore.getQuantity());
                productInStore.setQuantity(0);

                notifications.create()
                        .withCaption("Product " + productInStore.getProduct().getName() + " is out of stock")
                        .withType(Notifications.NotificationType.WARNING)
                        .show();

                log.info("Product {} is out of stock", productInStore.getProduct().getName());
                return;
            } else {
                productInStore.setQuantity(productInStore.getQuantity() - editedEntity.getQuantity());
                log.info("Decreased quantity of product {} by {}", productInStore.getProduct().getName(), editedEntity.getQuantity());
            }

            productsInPurchaseDc.getMutableItems().add(editedEntity);
            dataManager.commit(productInStore);
            log.info("Added/Updated product in purchase: {}", editedEntity.getProductInStore().getProduct().getName());
        }
    }
}