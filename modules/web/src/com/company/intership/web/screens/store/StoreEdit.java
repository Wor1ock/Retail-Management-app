package com.company.intership.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Store;

@UiController("intership_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
}