package com.company.intership.web.screens.productinpurchase;

import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("intership_ProductInPurchase.edit")
@UiDescriptor("product-in-purchase-edit.xml")
@EditedEntityContainer("productInPurchaseDc")
@LoadDataBeforeShow
@DialogMode(width = "AUTO", height = "AUTO", forceDialog = true)
public class ProductInPurchaseEdit extends StandardEditor<ProductInPurchase> {
    @Inject
    private PickerField<Purchase> purchaseField;
    @Inject
    private TextField<BigDecimal> priceField;
    @Inject
    private PickerField<ProductInStore> productInStorePicker;
    @Inject
    private CollectionLoader<ProductInStore> productsInStoreDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productsInStoreDl.setParameter("storeId", getEditedEntity().getPurchase().getStore().getId());
        productsInStoreDl.load();
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<ProductInPurchase> event) {
        event.getEntity().setQuantity(0);
        purchaseField.setEditable(false);
    }

    @Subscribe("productsInStoreTable")
    public void onProductInStoresTableSelection(Table.SelectionEvent<ProductInStore> event) {
        ProductInStore productInStore = event.getSelected().iterator().next();
        priceField.setValue(productInStore.getPrice());
        priceField.setEditable(false);
        productInStorePicker.setValue(productInStore);
        productInStorePicker.setEditable(false);
    }
}