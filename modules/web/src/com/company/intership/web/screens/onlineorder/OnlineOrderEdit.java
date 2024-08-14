package com.company.intership.web.screens.onlineorder;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;
import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.service.ExtendedUserService;
import com.company.intership.web.screens.purchase.PurchaseEdit;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.Target;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@UiController("intership_OnlineOrder.edit")
@UiDescriptor("online-order-edit.xml")
public class OnlineOrderEdit extends PurchaseEdit {
    @Inject
    private TextField<Integer> discountField;
    @Inject
    private CollectionContainer<ProductInPurchase> productsInPurchaseDc;
    @Inject
    private TextField<BigDecimal> orderTotalField;
    @Inject
    private PickerField<Customer> customerField;
    @Inject
    private UserSession userSession;
    @Inject
    private ExtendedUserService extendedUserService;

    @Override
    public OnlineOrder getEditedEntity() {
        return (OnlineOrder) super.getEditedEntity();
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (getEditedEntity().getCustomer() == null) {
            getEditedEntity()
                    .setCustomer(extendedUserService.getCustomer((ExtendedUser) userSession.getCurrentOrSubstitutedUser()));
            customerField.setEditable(false);
        }
    }

    @Subscribe("discountField")
    protected void onDiscountFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        OnlineOrder order = getEditedEntity();
        if (event.getValue() != null) {
            BigDecimal totalAmount = calculateTotalAmount();
            BigDecimal discount = new BigDecimal(event.getValue());
            BigDecimal discountedAmount = totalAmount
                    .subtract(totalAmount.multiply(discount.divide(new BigDecimal(100))));
            orderTotalField.setValue(discountedAmount);
            order.setOrderTotal(discountedAmount);
        }
    }

    private BigDecimal calculateTotalAmount() {
        List<ProductInPurchase> products = productsInPurchaseDc.getMutableItems();
        BigDecimal totalAmount = products.stream()
                .map(product -> product.getPrice().multiply(new BigDecimal(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (discountField.getValue() != null) {
            BigDecimal discount = BigDecimal.valueOf(discountField.getValue());
            BigDecimal discountMultiplier = BigDecimal.valueOf(100).subtract(discount).divide(BigDecimal.valueOf(100));
            return totalAmount.multiply(discountMultiplier);
        }
        return totalAmount;
    }

    @Subscribe(id = "productsInPurchaseDc", target = Target.DATA_CONTAINER)
    public void onProductsInPurchaseDcCollectionChange(CollectionContainer.CollectionChangeEvent<ProductInPurchase> event) {
        OnlineOrder order = getEditedEntity();
        BigDecimal totalAmount = calculateTotalAmount();
        order.setOrderTotal(totalAmount);
    }
}