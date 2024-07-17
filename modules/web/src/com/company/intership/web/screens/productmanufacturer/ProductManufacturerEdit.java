package com.company.intership.web.screens.productmanufacturer;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductManufacturer;

@UiController("intership_ProductManufacturer.edit")
@UiDescriptor("product-manufacturer-edit.xml")
@EditedEntityContainer("productManufacturerDc")
@LoadDataBeforeShow
public class ProductManufacturerEdit extends StandardEditor<ProductManufacturer> {
}