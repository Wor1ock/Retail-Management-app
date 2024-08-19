package com.company.intership.core.role;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = CashierRole.NAME)
public class CashierRole extends AnnotatedRoleDefinition {
    public static final String NAME = "Cashier";

    @ScreenAccess(screenIds = {
            "intership_Purchase.browse", "intership_Purchase.edit",
            "intership_ProductInPurchase.browse", "intership_ProductInPurchase.edit",
            "intership_Customer.browse",
            "intership_RandomProduct.browse",
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Purchase.class)
    @EntityAccess(entityClass = ProductInPurchase.class)
    @EntityAccess(entityClass = Customer.class)
    @EntityAccess(operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
}

