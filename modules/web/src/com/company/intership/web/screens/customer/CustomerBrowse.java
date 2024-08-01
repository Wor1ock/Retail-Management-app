package com.company.intership.web.screens.customer;

import com.company.intership.entity.Customer;
import com.company.intership.entity.IndividualCustomer;
import com.company.intership.entity.LegalEntityCustomer;
import com.company.intership.web.screens.individualcustomer.IndividualCustomerEdit;
import com.company.intership.web.screens.legalentitycustomer.LegalEntityCustomerEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("intership_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private CollectionContainer<Customer> customersDc;

    @Subscribe("createButton.createIndividualCustomerAction")
    protected void onCreateIndividualCustomerAction(Action.ActionPerformedEvent event) {
        screenBuilders.editor(IndividualCustomer.class, this)
                .newEntity()
                .withScreenClass(IndividualCustomerEdit.class)
                .withLaunchMode(OpenMode.DIALOG)
                .withAfterCloseListener(e -> {
                    Customer customer = e.getScreen().getEditedEntity();
                    customersDc.getMutableItems().add(customer);
                })
                .build()
                .show();
    }

    @Subscribe("createButton.createLegalEntityAction")
    protected void onCreateLegalEntityAction(Action.ActionPerformedEvent event) {
        screenBuilders.editor(LegalEntityCustomer.class, this)
                .newEntity()
                .withScreenClass(LegalEntityCustomerEdit.class)
                .withLaunchMode(OpenMode.DIALOG)
                .withAfterCloseListener(e -> {
                    Customer customer = e.getScreen().getEditedEntity();
                    customersDc.getMutableItems().add(customer);
                })
                .build()
                .show();
    }
}