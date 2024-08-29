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

@Role(name = ManufacturerRole.NAME)
public class ManufacturerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Manufacturer";

    @ScreenAccess(screenIds = {"intership_ProductManufacturer.browse", "intership_ProductManufacturer.edit", "intership_Product.browse", "intership_Product.edit", "intership_ManufacturerService.browse", "application-intership", "manageProduction", "intership_PriceHistory.browse", "intership_ManufacturerServiceBrowse", "bproc_MyTasks.browse", "bproc"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = TradeNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Store.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Purchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInPurchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = OnlineOrder.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = LegalEntityCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = IndividualCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ExtendedUser.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductManufacturer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = PriceHistory.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = TradeNetwork.class, view = "*")
    @EntityAttributeAccess(entityClass = Store.class, view = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInStore.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, view = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, view = "*")
    @EntityAttributeAccess(entityClass = LegalEntityCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = IndividualCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = ExtendedUser.class, view = "*")
    @EntityAttributeAccess(entityClass = Customer.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductManufacturer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

