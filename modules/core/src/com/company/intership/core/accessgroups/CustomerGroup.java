package com.company.intership.core.accessgroups;


import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.app.group.annotation.SessionAttribute;
import com.haulmont.cuba.security.group.ConstraintsContainer;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@AccessGroup(name = "CustomerGroup")
public class CustomerGroup extends AnnotatedAccessGroupDefinition {

    @JpqlConstraint(target = Purchase.class, where = "{E}.customer.user.id = :session$userCustomerId")
    @JpqlConstraint(target = OnlineOrder.class, where = "{E}.customer.user.id = :session$userCustomerId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }

    @SessionAttribute(name = "userCustomerId", value = "", javaClass = UUID.class)
    @Override
    public Map<String, Serializable> sessionAttributes() {
        return super.sessionAttributes();
    }
}
