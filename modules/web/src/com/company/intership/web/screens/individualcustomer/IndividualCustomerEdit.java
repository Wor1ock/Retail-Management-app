package com.company.intership.web.screens.individualcustomer;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.IndividualCustomer;

@UiController("intership_IndividualCustomer.edit")
@UiDescriptor("individual-customer-edit.xml")
@EditedEntityContainer("individualCustomerDc")
@LoadDataBeforeShow
public class IndividualCustomerEdit extends StandardEditor<IndividualCustomer> {
}