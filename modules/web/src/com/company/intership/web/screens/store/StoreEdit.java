package com.company.intership.web.screens.store;

import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Store;
import com.company.intership.web.screens.productinstore.ProductInStoreEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.builders.AfterScreenCloseEvent;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

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
    @Inject
    private DataContext dataContext;
    private static final Logger log = LoggerFactory.getLogger(StoreEdit.class);

    private void processAfterCloseEvent(AfterScreenCloseEvent<ProductInStoreEdit> afterCloseEvent) {
        if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
            ProductInStore productInStore = afterCloseEvent.getScreen().getEditedEntity();
            List<ProductInStore> productsInStoreList = productsDc.getMutableItems();
            boolean isDuplicate = false;

            // Проверка на дубликаты и обновление количества
            for (ProductInStore p : productsInStoreList) {
                if (p.getProduct().equals(productInStore.getProduct())
                        && !p.getId().equals(productInStore.getId())) {
                    p.setQuantity(p.getQuantity() + productInStore.getQuantity());
                    isDuplicate = true;
                    log.info("Duplicate product found: {}. Existing product ID: {}, New product ID: {}",
                            p.getProduct().getName(), p.getId(), productInStore.getId());
                    break;
                }
            }

            if (isDuplicate) {
                dataContext.remove(productInStore);
            }
            else {
                productsInStoreList.add(productInStore);
                log.info("Added new product to store: {} with quantity: {}", productInStore.getProduct().getName(), productInStore.getQuantity());
            }
            dataContext.commit();
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
                .withParentDataContext(dataContext)
                .withAfterCloseListener(this::processAfterCloseEvent)
                .build()
                .show();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productsDl.setParameter("storeId", getEditedEntity().getId());
        productsDl.load();
    }
}