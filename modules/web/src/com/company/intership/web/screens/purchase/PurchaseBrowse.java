package com.company.intership.web.screens.purchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Purchase;

@UiController("intership_Purchase.browse")
@UiDescriptor("purchase-browse.xml")
@LookupComponent("purchasesTable")
@LoadDataBeforeShow
public class PurchaseBrowse extends StandardLookup<Purchase> {
}