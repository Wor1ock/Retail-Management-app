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

    @ScreenAccess(screenIds = {"intership_Store.browse", "intership_Store.edit", "intership_Product.browse", "intership_Product.edit", "intership_ProductInStore.browse", "intership_ProductInStore.edit", "intership_Purchase.browse", "intership_Customer.browse", "intership_Customer.edit", "intership_StoreService", "intership_ProductInStoreService", "application-intership", "manageProduction", "intership_TradeNetwork.browse", "manageTradeNetwork", "intership_ProductManufacturer.browse", "intership_StoreServiceBrowse", "bproc_MyTasks.browse", "bproc", "bproc_DecisionDetailsScreen", "bproc_DefaultStartProcessForm", "bproc_DefaultTaskProcessForm", "bproc_DispatcherPropertiesFragment", "bproc_DmnDecisionTableEdit", "bproc_DynamicStartProcessForm", "bproc_DynamicTaskProcessForm", "bproc_DmnDecisionTable.lookup"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Product.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PriceHistory.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Purchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInPurchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = TradeNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductManufacturer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = OnlineOrder.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = LegalEntityCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = IndividualCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ExtendedUser.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Store.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Product.class, view = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, view = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, view = "*")
    @EntityAttributeAccess(entityClass = Customer.class, view = "*")
    @EntityAttributeAccess(entityClass = TradeNetwork.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductManufacturer.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInStore.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, view = "*")
    @EntityAttributeAccess(entityClass = LegalEntityCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = IndividualCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = ExtendedUser.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = Store.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
