package com.company.intership.web.screens.productmanufacturer;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductManufacturer;

@UiController("intership_ProductManufacturer.browse")
@UiDescriptor("product-manufacturer-browse.xml")
@LookupComponent("productManufacturersTable")
@LoadDataBeforeShow
public class ProductManufacturerBrowse extends StandardLookup<ProductManufacturer> {
}