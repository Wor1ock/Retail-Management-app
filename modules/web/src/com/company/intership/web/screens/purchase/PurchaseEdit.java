package com.company.intership.web.screens.purchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Purchase;

@UiController("intership_Purchase.edit")
@UiDescriptor("purchase-edit.xml")
@EditedEntityContainer("purchaseDc")
@LoadDataBeforeShow
public class PurchaseEdit extends StandardEditor<Purchase> {
}