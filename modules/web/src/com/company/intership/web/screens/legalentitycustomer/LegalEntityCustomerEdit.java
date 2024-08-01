package com.company.intership.web.screens.legalentitycustomer;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.LegalEntityCustomer;

@UiController("intership_LegalEntityCustomer.edit")
@UiDescriptor("legal-entity-customer-edit.xml")
@EditedEntityContainer("legalEntityCustomerDc")
@LoadDataBeforeShow
public class LegalEntityCustomerEdit extends StandardEditor<LegalEntityCustomer> {
}