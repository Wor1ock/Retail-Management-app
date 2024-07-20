package com.company.intership.web.screens.productinpurchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInPurchase;

@UiController("intership_ProductInPurchase.browse")
@UiDescriptor("product-in-purchase-browse.xml")
@LookupComponent("productInPurchasesTable")
@LoadDataBeforeShow
public class ProductInPurchaseBrowse extends StandardLookup<ProductInPurchase> {
}