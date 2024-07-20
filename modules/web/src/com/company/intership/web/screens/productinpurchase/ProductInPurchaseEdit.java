package com.company.intership.web.screens.productinpurchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInPurchase;

@UiController("intership_ProductInPurchase.edit")
@UiDescriptor("product-in-purchase-edit.xml")
@EditedEntityContainer("productInPurchaseDc")
@LoadDataBeforeShow
public class ProductInPurchaseEdit extends StandardEditor<ProductInPurchase> {
}