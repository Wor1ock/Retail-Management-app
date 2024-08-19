package com.company.intership.core.role;

import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.Product;
import com.company.intership.entity.ProductManufacturer;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
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
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = ProductManufacturer.class)
    @EntityAccess(entityClass = Product.class)
    @EntityAccess(entityClass = PriceHistory.class)
    @EntityAccess(operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

}

