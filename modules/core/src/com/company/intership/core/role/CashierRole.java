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

@Role(name = CashierRole.NAME)
public class CashierRole extends AnnotatedRoleDefinition {
    public static final String NAME = "Cashier";

    @ScreenAccess(screenIds = {"intership_Purchase.browse", "intership_ProductInPurchase.browse", "intership_Customer.browse", "intership_RandomProduct.browse", "application-intership", "intership_PriceHistory.browse", "intership_StoreServiceBrowse", "intership_Product.browse", "manageProduction", "bproc_MyTasks.browse", "bproc", "bproc_DecisionDetailsScreen", "bproc_DefaultStartProcessForm", "bproc_DefaultTaskProcessForm", "bproc_DmnDecisionTable.lookup", "bproc_DynamicStartProcessForm", "bproc_DynamicTaskProcessForm", "bproc_DispatcherPropertiesFragment"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = TradeNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Purchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInPurchase.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PriceHistory.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Store.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductManufacturer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Product.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = OnlineOrder.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = LegalEntityCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = IndividualCustomer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ExtendedUser.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = TradeNetwork.class, view = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, view = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, view = "*")
    @EntityAttributeAccess(entityClass = Customer.class, view = "*")
    @EntityAttributeAccess(entityClass = Store.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductManufacturer.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInStore.class, view = "*")
    @EntityAttributeAccess(entityClass = Product.class, view = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, view = "*")
    @EntityAttributeAccess(entityClass = LegalEntityCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = IndividualCustomer.class, view = "*")
    @EntityAttributeAccess(entityClass = ExtendedUser.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}

