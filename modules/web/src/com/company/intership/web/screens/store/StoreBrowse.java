package com.company.intership.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Store;

@UiController("intership_Store.browse")
@UiDescriptor("store-browse.xml")
@LookupComponent("storesTable")
@LoadDataBeforeShow
public class StoreBrowse extends StandardLookup<Store> {
}