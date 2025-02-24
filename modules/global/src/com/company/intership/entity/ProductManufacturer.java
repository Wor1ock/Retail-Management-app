package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "INTERSHIP_PRODUCT_MANUFACTURER")
@Entity(name = "intership_ProductManufacturer")
@NamePattern("%s|name")
public class ProductManufacturer extends StandardEntity {
    private static final long serialVersionUID = -2023981927744304110L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "building", column = @Column(name = "ADDRESS_BUILDING"))
    })
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}