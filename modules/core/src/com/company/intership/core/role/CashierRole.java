package com.company.intership.core.role;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = CashierRole.NAME)
public class CashierRole extends AnnotatedRoleDefinition {
    public static final String NAME = "Cashier";

    @ScreenAccess(screenIds = {"intership_Purchase.browse", "intership_ProductInPurchase.browse", "intership_Customer.browse", "intership_RandomProduct.browse", "application-intership", "intership_PriceHistory.browse", "intership_StoreServiceBrowse", "intership_Product.browse", "manageProduction"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductInPurchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

