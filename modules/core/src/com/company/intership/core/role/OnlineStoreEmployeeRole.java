package com.company.intership.core.role;

import com.company.intership.entity.Customer;
import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.Product;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = OnlineStoreEmployeeRole.NAME)
public class OnlineStoreEmployeeRole extends AnnotatedRoleDefinition {
    public static final String NAME = "OnlineStoreEmployee";

    @ScreenAccess(screenIds = {
            "intership_OnlineOrder.browse", "intership_OnlineOrder.edit",
            "intership_Customer.browse", "intership_Customer.edit",
            "intership_Product.browse", "intership_Product.edit",
            "intership_PriceHistory.browse",
            "intership_RandomProduct.browse",
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = OnlineOrder.class)
    @EntityAccess(entityClass = Product.class)
    @EntityAccess(entityClass = PriceHistory.class)
    @EntityAccess(entityClass = Customer.class)
    @EntityAccess(operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
}

