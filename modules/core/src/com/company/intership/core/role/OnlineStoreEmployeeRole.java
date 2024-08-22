package com.company.intership.core.role;

import com.company.intership.entity.Customer;
import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.Product;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = OnlineStoreEmployeeRole.NAME)
public class OnlineStoreEmployeeRole extends AnnotatedRoleDefinition {
    public static final String NAME = "OnlineStoreEmployee";

    @ScreenAccess(screenIds = {"intership_OnlineOrder.browse", "intership_OnlineOrder.edit", "intership_Customer.browse", "intership_Customer.edit", "intership_Product.browse", "intership_Product.edit", "intership_PriceHistory.browse", "intership_RandomProduct.browse", "application-intership", "manageProduction", "intership_Purchase.browse", "intership_ProductInPurchase.browse", "intership_StoreServiceBrowse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = PriceHistory.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = PriceHistory.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

