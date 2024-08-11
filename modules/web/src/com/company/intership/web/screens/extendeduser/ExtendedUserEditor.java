package com.company.intership.web.screens.extendeduser;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;
import com.company.intership.entity.IndividualCustomer;
import com.company.intership.entity.LegalEntityCustomer;
import com.company.intership.service.ExtendedUserService;
import com.company.intership.web.screens.individualcustomer.IndividualCustomerEdit;
import com.company.intership.web.screens.legalentitycustomer.LegalEntityCustomerEdit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.app.security.user.edit.UserEditor;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.StandardOutcome;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;

import javax.inject.Inject;

public class ExtendedUserEditor extends UserEditor {
    @Inject
    private DataManager dataManager;
    @Inject
    private Dialogs dialogs;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Logger log;
    @Inject
    private ExtendedUserService extendedUserService;

    @Override
    public ExtendedUser getEditedEntity() {
        return (ExtendedUser) super.getEditedEntity();
    }

    @Subscribe
    public void onBeforeClose(Screen.BeforeCloseEvent event) {
        ExtendedUser user = getEditedEntity();
        if (extendedUserService.hasCustomerForUser(user)) {
            showCustomerCreationDialog();
        } else {
            dataManager.commit(user);
        }
    }


    private void showCustomerCreationDialog() {
        dialogs.createOptionDialog()
                .withCaption("Create Customer")
                .withMessage("Do you want to create a Physical or Legal entity?")
                .withActions(
                        new DialogAction(DialogAction.Type.YES)
                                .withHandler(e -> openCustomerCreationScreen()),
                        new DialogAction(DialogAction.Type.NO)
                                .withHandler(e -> dataManager.commit(getEditedEntity()))
                )
                .show();
    }

    private void openCustomerCreationScreen() {
        dialogs.createOptionDialog()
                .withCaption("Select Customer type")
                .withMessage("Do you want to create a Physical or Legal entity?")
                .withActions(
                        new DialogAction(DialogAction.Type.YES)
                                .withCaption("Создать физическое лицо")
                                .withHandler(e -> openIndividualCustomerCreationScreen()),
                        new DialogAction(DialogAction.Type.NO)
                                .withCaption("Создать юридическое лицо")
                                .withHandler(e -> openLegalEntityCustomerCreationScreen())
                )
                .show();
    }

    private void openIndividualCustomerCreationScreen() {
        screenBuilders.editor(IndividualCustomer.class, this)
                .withInitializer(this::initializeCustomer)
                .withScreenClass(IndividualCustomerEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                        IndividualCustomer customer = (IndividualCustomer) afterCloseEvent.getScreen().getEditedEntity();
                        getEditedEntity().setCustomer(customer);
                        customer.setExtendedUser(getEditedEntity());
                        dataManager.commit(customer);
                    }
                })
                .build()
                .show();
    }

    private void openLegalEntityCustomerCreationScreen() {
        screenBuilders.editor(LegalEntityCustomer.class, this)
                .newEntity()
                .withInitializer(this::initializeCustomer)
                .withScreenClass(LegalEntityCustomerEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                        LegalEntityCustomer customer = (LegalEntityCustomer) afterCloseEvent.getScreen().getEditedEntity();
                        getEditedEntity().setCustomer(customer);
                        customer.setExtendedUser(getEditedEntity());
                        dataManager.commit(customer);
                    }
                })
                .build()
                .show();
    }


    private void initializeCustomer(Customer customer) {
        User user = getEditedEntity();
        customer.setFullName(user.getName());
        customer.setEmail(user.getEmail());
        if (customer instanceof IndividualCustomer) {
            IndividualCustomer individualCustomer = (IndividualCustomer) customer;
            individualCustomer.setFirstName(user.getFirstName());
            individualCustomer.setLastName(user.getLastName());
            individualCustomer.setMiddleName(user.getMiddleName());
        }
    }
}
