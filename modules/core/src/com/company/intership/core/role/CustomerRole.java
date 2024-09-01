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

@Role(name = CustomerRole.NAME)
public class CustomerRole extends AnnotatedRoleDefinition {
    public static final String NAME = "Customer";

    @ScreenAccess(screenIds = {"intership_Purchase.browse", "intership_OnlineOrder.browse", "intership_OnlineOrder.edit", "intership_RandomProduct.browse", "application-intership", "intership_ProductInPurchase.browse", "intership_ProductInPurchase.edit", "intership_Purchase.edit", "bproc_MyTasks.browse", "bproc", "intership_Product.browse", "manageProduction", "intership_ProductManufacturer.browse", "intership_Store.browse", "manageTradeNetwork", "intership_TradeNetwork.browse", "bproc_DefaultTaskProcessForm", "bproc_DecisionDetailsScreen", "bproc_DynamicTaskProcessForm", "bproc_DynamicStartProcessForm", "bproc_DmnDecisionTable.lookup", "bproc_DefaultStartProcessForm"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }


    @EntityAccess(entityClass = ExtendedUser.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Product.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = TradeNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Store.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductManufacturer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PriceHistory.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInPurchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = LegalEntityCustomer.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = IndividualCustomer.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Address.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = ExtendedUser.class, view = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = TradeNetwork.class, modify = "*")
    @EntityAttributeAccess(entityClass = Store.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductManufacturer.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInStore.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, modify = "*")
    @EntityAttributeAccess(entityClass = LegalEntityCustomer.class, modify = "*")
    @EntityAttributeAccess(entityClass = IndividualCustomer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
