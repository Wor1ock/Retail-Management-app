package com.company.intership.web.screens.store;

import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Store;

import javax.inject.Inject;

@UiController("intership_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
    @Inject
    private CollectionLoader<ProductInStore> productsDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productsDl.setParameter("storeId", getEditedEntity().getId());
        productsDl.load();
    }
}