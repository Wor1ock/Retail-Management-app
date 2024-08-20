package com.company.intership.core.role;

import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.Product;
import com.company.intership.entity.ProductManufacturer;
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

    @ScreenAccess(screenIds = {
            "intership_ProductManufacturer.browse", "intership_ProductManufacturer.edit",
            "intership_Product.browse", "intership_Product.edit",
            "intership_PriceHistory.browse",
            "intership_ManufacturerService.browse",
            "application-intership",
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = ProductManufacturer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = PriceHistory.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = ProductManufacturer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

