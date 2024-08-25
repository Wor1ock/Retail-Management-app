package com.company.intership.core.accessgroups;

import com.company.intership.entity.Store;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = "StoreGroup")
public class StoreGroup extends AnnotatedAccessGroupDefinition {

    @JpqlConstraint(target = Store.class, where = "{E}.createdBy = :session$userLogin")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}

