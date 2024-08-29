package com.company.intership.core.role;

import com.company.intership.entity.*;
import com.haulmont.addon.bproc.entity.DmnDecisionTableData;
import com.haulmont.addon.bproc.entity.DmnHistoricDecisionExecutionData;
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

    @ScreenAccess(screenIds = {"intership_OnlineOrder.browse", "intership_OnlineOrder.edit", "intership_Customer.browse", "intership_Customer.edit", "intership_Product.browse", "intership_Product.edit", "intership_PriceHistory.browse", "intership_RandomProduct.browse", "application-intership", "manageProduction", "intership_Purchase.browse", "intership_ProductInPurchase.browse", "intership_StoreServiceBrowse", "bproc", "bproc_MyTasks.browse", "bproc_DecisionDetailsScreen", "bproc_DefaultStartProcessForm", "bproc_DefaultTaskProcessForm", "bproc_DmnDecisionTable.lookup", "bproc_DynamicStartProcessForm", "bproc_DynamicTaskProcessForm", "bproc_DispatcherPropertiesFragment", "bproc_DmnDecisionTableEdit", "intership_TradeNetwork.browse", "manageTradeNetwork", "intership_Store.browse", "intership_ProductManufacturer.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = OnlineOrder.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = DmnHistoricDecisionExecutionData.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = DmnDecisionTableData.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityName = "bproc_DecisionTableModel", operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = TradeNetwork.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = Store.class, operations = {EntityOp.READ, EntityOp.UPDATE, EntityOp.CREATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Purchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductManufacturer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductInPurchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = LegalEntityCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = IndividualCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ExtendedUser.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PriceHistory.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = ProductInStore.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, view = "*")
    @EntityAttributeAccess(entityClass = Customer.class, view = "*")
    @EntityAttributeAccess(entityClass = DmnHistoricDecisionExecutionData.class, modify = "*")
    @EntityAttributeAccess(entityClass = DmnDecisionTableData.class, modify = "*")
    @EntityAttributeAccess(entityName = "bproc_DecisionTableModel", modify = "*")
    @EntityAttributeAccess(entityClass = TradeNetwork.class, modify = "*")
    @EntityAttributeAccess(entityClass = Store.class, modify = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductManufacturer.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, view = "*")
    @EntityAttributeAccess(entityClass = LegalEntityCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = IndividualCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = ExtendedUser.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

