package com.company.intership.core.role;

import com.company.intership.entity.Customer;
import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Product;
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

@Role(name = CustomerRole.NAME)
public class CustomerRole extends AnnotatedRoleDefinition {
    public static final String NAME = "Customer";

    @ScreenAccess(screenIds = {"intership_Purchase.browse", "intership_OnlineOrder.browse", "intership_OnlineOrder.edit", "intership_Product.browse", "intership_RandomProduct.browse", "application-intership", "manageTradeNetwork", "manageProduction", "intership_Store.browse", "intership_ProductInPurchase.browse", "intership_StoreServiceBrowse", "intership_ProductInPurchase.edit", "intership_Purchase.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }


    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
