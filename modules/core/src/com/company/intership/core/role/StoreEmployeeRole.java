package com.company.intership.core.role;

import com.company.intership.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
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
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Store.class)
    @EntityAccess(entityClass = Product.class)
    @EntityAccess(entityClass = ProductInStore.class)
    @EntityAccess(entityClass = Purchase.class)
    @EntityAccess(entityClass = Customer.class)
    @EntityAccess(operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

}
