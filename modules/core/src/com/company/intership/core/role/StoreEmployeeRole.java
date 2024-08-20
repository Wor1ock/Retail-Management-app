package com.company.intership.core.role;

import com.company.intership.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = StoreEmployeeRole.NAME)
public class StoreEmployeeRole extends AnnotatedRoleDefinition {
    public final static String NAME = "StoreEmployee";

    @ScreenAccess(screenIds = {
            "intership_Store.browse", "intership_Store.edit",
            "intership_Product.browse", "intership_Product.edit",
            "intership_ProductInStore.browse", "intership_ProductInStore.edit",
            "intership_Purchase.browse", "intership_Purchase.edit",
            "intership_Customer.browse", "intership_Customer.edit",
            "intership_StoreService", "intership_ProductInStoreService",
            "application-intership",
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Store.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductInPurchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Store.class, modify = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
