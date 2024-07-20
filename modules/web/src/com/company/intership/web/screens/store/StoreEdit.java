package com.company.intership.web.screens.store;

import com.company.intership.entity.ProductInStore;
import com.company.intership.web.screens.productinstore.ProductInStoreEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.builders.AfterScreenCloseEvent;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@UiController("intership_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private GroupTable<ProductInStore> productsTable;
    @Inject
    private CollectionLoader<ProductInStore> productsDl;
    @Inject
    private CollectionContainer<ProductInStore> productsDc;
    private static final Logger log = LoggerFactory.getLogger(StoreEdit.class);
    private void processAfterCloseEvent(AfterScreenCloseEvent<ProductInStoreEdit> afterCloseEvent) {
        if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
            ProductInStore productInStore = afterCloseEvent.getScreen().getEditedEntity();
            // Временно удаляем новую сущность из коллекции
            productsDc.getMutableItems().remove(productInStore);

            // Проверка на дубликаты и обновление количества
            boolean isDuplicate = false;
            for (ProductInStore p : productsDc.getMutableItems()) {
                if (p.getProduct().equals(productInStore.getProduct())
                        && p.getPrice().compareTo(productInStore.getPrice()) == 0) {
                    p.setQuantity(p.getQuantity() + productInStore.getQuantity());
                    isDuplicate = true;
                    log.info("Updated quantity for existing product: {}. New quantity: {}",
                            p.getProduct().getName(), p.getQuantity());
                    break;
                }
            }
            if (!isDuplicate) {
                productsDc.getMutableItems().add(productInStore);
                log.info("Added new product to store: {} with quantity: {}",
                        productInStore.getProduct().getName(), productInStore.getQuantity());
            }
        }
    }
    @Subscribe("productsTable.create")
    protected void onProductsTableCreate(Action.ActionPerformedEvent event) {
        screenBuilders.editor(productsTable)
                .newEntity()
                .withInitializer(productInStore -> {
                    productInStore.setStore(getEditedEntity());
                })
                .withScreenClass(ProductInStoreEdit.class)
                .withLaunchMode(OpenMode.DIALOG)
                .withAfterCloseListener(afterCloseEvent -> processAfterCloseEvent(afterCloseEvent))
                .build()
                .show();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productsDl.setParameter("storeId", getEditedEntity().getId());
        productsDl.load();
    }
}