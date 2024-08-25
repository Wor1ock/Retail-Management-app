package com.company.intership.core.accessgroups;

import com.company.intership.entity.Product;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.app.group.annotation.SessionAttribute;
import com.haulmont.cuba.security.group.ConstraintsContainer;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@AccessGroup(name = "ManufacturerGroup")
public class ManufacturerGroup extends AnnotatedAccessGroupDefinition {

    @JpqlConstraint(target = Product.class, where = "{E}.manufacturer.user.id = :session$userId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}

